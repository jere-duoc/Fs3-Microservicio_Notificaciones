package DuocQuin.Notificaciones.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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

        return fallback;
    }

    //Listar notificciones
    public List<NotificacionModel> listarNotificaciones() {
        return notificacionesRepository.findAll();
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

    //Nottificacion automatica generada por la creacion de horario
    @CircuitBreaker(name = "notificaciones", fallbackMethod = "fallbackCrearDesdeHorario")
    public NotificacionModel crearDesdeHorario(
            Long idHorario,
            Long idUsuario,
            String mensaje,
            TipoEnvio tipoEnvio) {

        if (mensaje == null || mensaje.isBlank()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacio");
        }

        if (tipoEnvio == null) {
            tipoEnvio = TipoEnvio.PLATAFORMA;
        }

        NotificacionModel model = new NotificacionModel();

        model.setIdHorario(idHorario);
        model.setIdUsuario(idUsuario);
        model.setMensaje(mensaje);
        model.setTipoEnvio(tipoEnvio);

        procesarNotificacion(model);

        return notificacionesRepository.save(model);
    }

    //fallback notificacion automatica
    public NotificacionModel fallbackCrearDesdeHorario(
            Long idHorario,
            Long idUsuario,
            String mensaje,
            TipoEnvio tipoEnvio,
            Exception e) {

        NotificacionModel fallback = new NotificacionModel();
        fallback.setIdHorario(idHorario);
        fallback.setIdUsuario(idUsuario);
        fallback.setMensaje("Notificacion pendiente por caida del servicio");
        fallback.setTipoEnvio(TipoEnvio.PLATAFORMA);
        fallback.setFechaEnvio(LocalDateTime.now());
        fallback.setLeida(false);

        return fallback;
    }
}