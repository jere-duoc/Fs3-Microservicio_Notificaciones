package DuocQuin.Notificaciones.plataformaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import DuocQuin.Notificaciones.model.NotificacionModel;
import DuocQuin.Notificaciones.notificaciones.NotificacionGmail;
import DuocQuin.Notificaciones.notificaciones.NotificacionPlataforma;
import DuocQuin.Notificaciones.notificaciones.NotificacionWhatsapp;

class NotificacionPlataformaTest {

    @Test
    void retornarPlataformaOk() {
        NotificacionPlataforma notificacion =
                new NotificacionPlataforma();

        String resultado =
                notificacion.tipoEnvioMensaje(
                        new NotificacionModel());

        assertEquals("PLATAFORMA OK", resultado);
    }

    @Test
    void retornarGmailOk() {
        NotificacionGmail notificacion =
                new NotificacionGmail();

        String resultado =
                notificacion.tipoEnvioMensaje(
                        new NotificacionModel());

        assertEquals("GMAIL OK", resultado);
    }

    @Test
    void retornarWhatsappOk() {
        NotificacionWhatsapp notificacion =
                new NotificacionWhatsapp();

        String resultado =
                notificacion.tipoEnvioMensaje(
                        new NotificacionModel());

        assertEquals("WHATSAPP OK", resultado);
    }
}