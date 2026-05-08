package DuocQuin.Notificaciones.notificaciones;

import DuocQuin.Notificaciones.model.NotificacionModel;

public interface Notificacion {
    String tipoEnvioMensaje(NotificacionModel notificacionModel);

}
