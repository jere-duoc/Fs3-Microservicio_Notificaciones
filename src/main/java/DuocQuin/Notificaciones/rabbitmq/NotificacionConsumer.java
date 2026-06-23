/**package DuocQuin.Notificaciones.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import DuocQuin.Notificaciones.dto.EventoHorarioDTO;
import DuocQuin.Notificaciones.dto.EventoPagosDTO;
import DuocQuin.Notificaciones.model.TipoEnvio;
import DuocQuin.Notificaciones.service.NotificacionesService;

@Component
public class NotificacionConsumer {

    @Autowired
    private NotificacionesService notificacionesService;

    @RabbitListener(queues = "cola_horarios")
    public void recibirEventos(EventoHorarioDTO evento){

        System.out.println("=========================================");
        System.out.println("EVENTO HORARIO RECIBIDO");
        System.out.println("Horario: " + evento.getIdHorario());
        System.out.println("Usuario: " + evento.getIdUsuario());
        System.out.println("=========================================");


        notificacionesService.crearDesdeHorario(
            evento.getIdHorario(),
            evento.getIdUsuario(),
            evento.getMensaje(),
            evento.getTipoEnvio()
        );
    }

    @RabbitListener(queues = "cola_pagos")
    public void recibirPago(EventoPagosDTO evento){
        System.out.println("=========================================");
        System.out.println("EVENTO PAGO RECIBIDO");
        System.out.println("Horario: " + evento.getIdSueldo());
        System.out.println("Usuario: " + evento.getIdUsuario());
        System.out.println("=========================================");
        notificacionesService.crearDesdePago(
                evento.getIdSueldo(),
                evento.getIdUsuario(),
                evento.getMensaje(),
                TipoEnvio.PLATAFORMA
        );

    }
}**/