package DuocQuin.Notificaciones.notificaciones;

import org.springframework.stereotype.Component;

import DuocQuin.Notificaciones.model.NotificacionModel;

@Component
public class NotificacionWhatsapp
        implements Notificacion {

    @Override
    public String tipoEnvioMensaje(
            NotificacionModel notificacionModel){

        System.out.println(
            "Enviando notificacion por whatsapp"
        );

        return "WHATSAPP OK";
    }
}