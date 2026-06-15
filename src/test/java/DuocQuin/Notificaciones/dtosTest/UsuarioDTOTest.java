package DuocQuin.Notificaciones.dtosTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DuocQuin.Notificaciones.dto.UsuarioDTO;

class UsuarioDTOTest {

    @Test
    void gettersYSetters() {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setIdUsuario(1L);
        dto.setPrimerNombre("Juan");
        dto.setSegundoNombre("Pérez");
        dto.setCorreoElectronico("juan@test.cl");
        dto.setTelefonoCelular("999999999");

        assertEquals(1L, dto.getIdUsuario());
        assertEquals("Juan", dto.getPrimerNombre());
        assertEquals("Pérez", dto.getSegundoNombre());
        assertEquals("juan@test.cl", dto.getCorreoElectronico());
        assertEquals("999999999", dto.getTelefonoCelular());
    }
}