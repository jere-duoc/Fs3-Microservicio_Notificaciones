package DuocQuin.Notificaciones.dtosTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DuocQuin.Notificaciones.dto.EventoPagosDTO;

class EventoPagosDTOTest {

    @Test
    void gettersYSetters() {
        EventoPagosDTO dto = new EventoPagosDTO();

        dto.setIdSueldo(1L);
        dto.setIdUsuario(2L);
        dto.setMensaje("Pago generado");

        assertEquals(1L, dto.getIdSueldo());
        assertEquals(2L, dto.getIdUsuario());
        assertEquals("Pago generado", dto.getMensaje());
    }

    @Test
    void constructorCompleto() {
        EventoPagosDTO dto = new EventoPagosDTO(
                        1L,
                        2L,
                        "Pago generado");

        assertEquals(1L, dto.getIdSueldo());
        assertEquals(2L, dto.getIdUsuario());
        assertEquals("Pago generado", dto.getMensaje());
    }
}