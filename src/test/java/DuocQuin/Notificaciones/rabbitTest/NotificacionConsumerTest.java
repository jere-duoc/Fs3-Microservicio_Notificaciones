/*package DuocQuin.Notificaciones.rabbitTest;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import DuocQuin.Notificaciones.dto.EventoHorarioDTO;
import DuocQuin.Notificaciones.dto.EventoPagosDTO;
import DuocQuin.Notificaciones.model.TipoEnvio;
import DuocQuin.Notificaciones.rabbitmq.NotificacionConsumer;
import DuocQuin.Notificaciones.service.NotificacionesService;

@ExtendWith(MockitoExtension.class)
class NotificacionConsumerTest {

    @InjectMocks
    private NotificacionConsumer consumer;

    @Mock
    private NotificacionesService notificacionesService;


    @Test
    void recibirEventoHorario() {
        EventoHorarioDTO evento = new EventoHorarioDTO();

        evento.setIdHorario(1L);
        evento.setIdUsuario(10L);
        evento.setMensaje("Horario creado");
        evento.setTipoEnvio(TipoEnvio.PLATAFORMA);

        consumer.recibirEventos(evento);

        verify(notificacionesService)
                .crearDesdeHorario(
                        1L,
                        10L,
                        "Horario creado",
                        TipoEnvio.PLATAFORMA);
    }


    @Test
    void recibirEventoPago() {
        EventoPagosDTO evento = new EventoPagosDTO();

        evento.setIdSueldo(5L);
        evento.setIdUsuario(10L);
        evento.setMensaje("Pago generado");

        consumer.recibirPago(evento);

        verify(notificacionesService)
                .crearDesdePago(
                        5L,
                        10L,
                        "Pago generado",
                        TipoEnvio.PLATAFORMA);
    }
}

*/