package DuocQuin.Notificaciones.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import DuocQuin.Notificaciones.model.TipoEnvio;
import DuocQuin.Notificaciones.notificaciones.Notificacion;
import DuocQuin.Notificaciones.notificaciones.NotificacionGmail;
import DuocQuin.Notificaciones.notificaciones.NotificacionPlataforma;
import DuocQuin.Notificaciones.notificaciones.NotificacionWhatsapp;

@Component
public class NotificacionFactory {
    @Autowired
    private NotificacionPlataforma notificacionPlataforma;
    @Autowired
    private NotificacionGmail notificacionGmail;
    @Autowired
    private NotificacionWhatsapp notificacionWhatsapp;

    public Notificacion obtener(TipoEnvio tipoEnvio) {
        switch (tipoEnvio) {
            case PLATAFORMA:
                return notificacionPlataforma;
            case GMAIL:
                return notificacionGmail;
            case WHATSAPP:
                return notificacionWhatsapp;
            default:
                throw new IllegalArgumentException("Tipo de envio invalido");
        }
    }
}