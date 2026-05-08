package DuocQuin.Notificaciones.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import DuocQuin.Notificaciones.dto.EventoHorarioDTO;
import DuocQuin.Notificaciones.service.NotificacionesService;

@Component
public class NotificacionConsumer {

    @Autowired
    private NotificacionesService notificacionesService;

    @RabbitListener(queues = "cola_notificaciones")
    public void recibirEventos(EventoHorarioDTO evento){

        System.out.println("Evento recibido desde horario");

        notificacionesService.crearDesdeHorario(
            evento.getIdHorario(),
            evento.getIdUsuario(),
            evento.getMensaje(),
            evento.getTipoEnvio()
        );
    }
}