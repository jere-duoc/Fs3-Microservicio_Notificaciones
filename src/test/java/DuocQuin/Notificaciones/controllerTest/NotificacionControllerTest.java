package DuocQuin.Notificaciones.controllerTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import DuocQuin.Notificaciones.controller.NotificacionesController;
import DuocQuin.Notificaciones.model.NotificacionModel;
import DuocQuin.Notificaciones.service.NotificacionesService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificacionesController.class)
class NotificacionControllerTest {
    

    @Autowired
    private MockMvc mockMvc;
    @Autowired

    @MockBean
    private NotificacionesService notificacionesService;

    @Test
    void listar() throws Exception{
        NotificacionModel n = new NotificacionModel();
        n.setIdNotificacion(1L);

        when(notificacionesService.listarNotificaciones()).thenReturn(List.of(n));

        mockMvc.perform(get("/api/notificaciones")).andExpect(status().isOk());

        verify(notificacionesService).listarNotificaciones();
    }

    @Test
    void obtenerNotificacionPorId() throws Exception{
        NotificacionModel n = new NotificacionModel();
        n.setIdNotificacion(1L);

        when(notificacionesService.obtenerPorId(1L)).thenReturn(Optional.of(n));

        mockMvc.perform(get("/api/notificaciones/1")).andExpect(status().isOk());

        verify(notificacionesService).obtenerPorId(1L);
    }


    @Test
    void eliminarNotificacion() throws Exception {
        doNothing().when(notificacionesService).eliminarNotificacion(1L);

        mockMvc.perform(delete("/api/notificaciones/1")).andExpect(status().isOk());

        verify(notificacionesService).eliminarNotificacion(1L);
    }
}
