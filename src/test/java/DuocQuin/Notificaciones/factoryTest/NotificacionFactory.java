package DuocQuin.Notificaciones.factoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import DuocQuin.Notificaciones.factory.NotificacionFactory;
import DuocQuin.Notificaciones.model.TipoEnvio;
import DuocQuin.Notificaciones.notificaciones.Notificacion;
import DuocQuin.Notificaciones.notificaciones.NotificacionGmail;
import DuocQuin.Notificaciones.notificaciones.NotificacionPlataforma;
import DuocQuin.Notificaciones.notificaciones.NotificacionWhatsapp;

@ExtendWith(MockitoExtension.class)
class NotificacionFactoryTest {

@InjectMocks
private NotificacionFactory factory;

@Mock
private NotificacionPlataforma notificacionPlataforma;

@Mock
private NotificacionGmail notificacionGmail;

@Mock
private NotificacionWhatsapp notificacionWhatsapp;


@Test
void obtenerDebeRetornarNotificacionPlataforma() {
    Notificacion resultado = factory.obtener(TipoEnvio.PLATAFORMA);
    assertEquals(notificacionPlataforma, resultado);
}


@Test
void retornarNotificacionGmail() {
    Notificacion resultado = factory.obtener(TipoEnvio.GMAIL);
    assertEquals(notificacionGmail, resultado);
}

@Test
void retornarNotificacionWhatsapp() {
    Notificacion resultado = factory.obtener(TipoEnvio.WHATSAPP);
    assertEquals(notificacionWhatsapp, resultado);
}

@Test
void lanzarExcepcionCuandoTipoEsNull() {
    assertThrows(NullPointerException.class, () -> factory.obtener(null));
}
}
