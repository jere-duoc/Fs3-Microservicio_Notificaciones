package DuocQuin.Notificaciones.dtosTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DuocQuin.Notificaciones.dto.EventoHorarioDTO;
import DuocQuin.Notificaciones.model.TipoEnvio;

class EventoHorarioDTOTest {

    @Test
    void gettersYSetters() {
        EventoHorarioDTO dto = new EventoHorarioDTO();

        dto.setIdHorario(1L);
        dto.setIdUsuario(2L);
        dto.setMensaje("Horario creado");
        dto.setTipoEnvio(TipoEnvio.PLATAFORMA);

        assertEquals(1L, dto.getIdHorario());
        assertEquals(2L, dto.getIdUsuario());
        assertEquals("Horario creado", dto.getMensaje());
        assertEquals(TipoEnvio.PLATAFORMA, dto.getTipoEnvio());
    }

    @Test
    void constructorCompleto() {
        EventoHorarioDTO dto = new EventoHorarioDTO(
                        1L,
                        2L,
                        "Horario creado",
                        TipoEnvio.GMAIL);

        assertEquals(1L, dto.getIdHorario());
        assertEquals(2L, dto.getIdUsuario());
        assertEquals("Horario creado", dto.getMensaje());
        assertEquals(TipoEnvio.GMAIL, dto.getTipoEnvio());
    }
}