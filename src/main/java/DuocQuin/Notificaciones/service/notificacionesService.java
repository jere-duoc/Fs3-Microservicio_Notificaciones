package DuocQuin.Notificaciones.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import DuocQuin.Notificaciones.dto.UsuarioDTO;
import DuocQuin.Notificaciones.factory.NotificacionFactory;
import DuocQuin.Notificaciones.model.NotificacionModel;
import DuocQuin.Notificaciones.model.TipoEnvio;
import DuocQuin.Notificaciones.notificaciones.Notificacion;
import DuocQuin.Notificaciones.repository.NotificacionesRepository;

@Service
@Transactional
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    @Autowired
    private NotificacionFactory notificacionFactory;

    @Autowired
    private UsuarioClient usuarioClient;

    private static final Logger logger = LoggerFactory.getLogger(NotificacionesService.class);


    //procesar notificaciones 
    private void procesarNotificacion(NotificacionModel model) {

        if (model.getTipoEnvio() == null) {
            model.setTipoEnvio(TipoEnvio.PLATAFORMA);
        }

        model.setFechaEnvio(LocalDateTime.now());
        model.setLeida(false);

        Notificacion servicio =
            notificacionFactory.obtener(model.getTipoEnvio());

        servicio.tipoEnvioMensaje(model);
    }

    //Crear de notificacion forma manual(admin)
    @CircuitBreaker(name = "notificaciones", fallbackMethod = "fallbackCrearManual")
    public NotificacionModel crearManual(NotificacionModel notificacionModel) {

        if (notificacionModel.getMensaje() == null || notificacionModel.getMensaje().isBlank()) {
            throw new IllegalArgumentException("El mensaje es obligatorio");
        }

        if (notificacionModel.getTipoEnvio() == null) {
            throw new IllegalArgumentException("Debe especificar tipo de envio");
        }

        if(notificacionModel.getTipoEnvio() == TipoEnvio.GMAIL ||
            notificacionModel.getTipoEnvio() == TipoEnvio.WHATSAPP){

                if(notificacionModel.getIdUsuario() == null){
                    throw new IllegalArgumentException("El idUsuario es obligatorio");
                }

                UsuarioDTO usuario = usuarioClient.obtenerUsuario(notificacionModel.getIdUsuario());
                logger.info("Usuario obtenido {}", usuario.getPrimerNombre());
            }
            

        procesarNotificacion(notificacionModel);
        return notificacionesRepository.save(notificacionModel);
    }

    //fallback de notificacion manual
    public NotificacionModel fallbackCrearManual(NotificacionModel notificacionModel, Exception e) {
        NotificacionModel fallback = new NotificacionModel();
        fallback.setMensaje("Servicio de notificaciones no disponible");
        fallback.setFechaEnvio(LocalDateTime.now());
        fallback.setLeida(false);
        fallback.setTipoEnvio(TipoEnvio.PLATAFORMA);

        logger.error("Fallback activado: {}", e.getMessage());
        return fallback;
    }

    //Listar notificciones
    public List<NotificacionModel> listarNotificaciones() {
        return notificacionesRepository.findAll();
    }

    //Listar notificacion por id
    public Optional<NotificacionModel> obtenerPorId(Long id_notificacion){
        return notificacionesRepository.findById(id_notificacion);
    }

    //Eliminar notificacion(OPCIONAL)
    public void eliminarNotificacion(Long id) {

        if (!notificacionesRepository.existsById(id)) {
            throw new IllegalArgumentException("Notificacion no encontrada");
        }
        notificacionesRepository.deleteById(id);
    }

    //Editar notificacion (OPCIONAL)
    public NotificacionModel editarNotificacion(NotificacionModel notificacionModel) {

        if (notificacionModel.getIdNotificacion() == null) {
            throw new IllegalArgumentException("ID de notificacion requerido");
        }

        return notificacionesRepository.save(notificacionModel);
    }

    // Método crearDesdeHorario eliminado - RabbitMQ removido del sistema
}
