package DuocQuin.Notificaciones.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import DuocQuin.Notificaciones.dto.UsuarioDTO;
import DuocQuin.Notificaciones.factory.NotificacionFactory;
import DuocQuin.Notificaciones.model.NotificacionModel;
import DuocQuin.Notificaciones.model.TipoEnvio;
import DuocQuin.Notificaciones.notificaciones.Notificacion;
import DuocQuin.Notificaciones.repository.NotificacionesRepository;
import DuocQuin.Notificaciones.service.NotificacionesService;
import DuocQuin.Notificaciones.service.UsuarioClient;

@ExtendWith(MockitoExtension.class)
class NotificacionesServiceTest {

    @Mock
    private NotificacionesRepository repository;

    @Mock
    private NotificacionFactory factory;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private Notificacion notificacion;

    @InjectMocks
    private NotificacionesService service;

    @Test
    void listarNotificaciones() {
        List<NotificacionModel> lista = List.of(new NotificacionModel(), new NotificacionModel());

        when(repository.findAll()).thenReturn(lista);

        List<NotificacionModel> resultado = service.listarNotificaciones();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void obtenerNotificacionPorId() {
        NotificacionModel n = new NotificacionModel();

        n.setIdNotificacion(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(n));

        Optional<NotificacionModel> resultado = service.obtenerPorId(1L);

        assertTrue(resultado.isPresent());

        assertEquals(1L, resultado.get().getIdNotificacion());
    }

    @Test
    void eliminarNotificacion() {
        when(repository.existsById(1L)).thenReturn(true);

        service.eliminarNotificacion(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void errorEliminarNotificacionNoExistente() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.eliminarNotificacion(1L));
    }

    @Test
    void crearNotificacionPorHorario() {
        when(factory.obtener(TipoEnvio.PLATAFORMA)).thenReturn(notificacion);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        NotificacionModel resultado = service.crearDesdeHorario(
            1L, 
            2L, 
            "Horario creado", 
            TipoEnvio.PLATAFORMA);

        assertEquals(1L, resultado.getIdHorario());

        verify(repository).save(any());
        verify(notificacion).tipoEnvioMensaje(any());
    }

    @Test
    void crearNotificacionManual() {
        NotificacionModel n = new NotificacionModel();

        n.setMensaje("Hola");
        n.setTipoEnvio(TipoEnvio.PLATAFORMA);

        when(factory.obtener(TipoEnvio.PLATAFORMA)).thenReturn(notificacion);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        NotificacionModel resultado = service.crearManual(n);

        assertEquals("Hola", resultado.getMensaje());
        verify(repository).save(any());
    }

    @Test
    void crearManualMensajeVacio() {
        NotificacionModel n = new NotificacionModel();

        n.setMensaje("");
        n.setTipoEnvio(TipoEnvio.PLATAFORMA);

        assertThrows(IllegalArgumentException.class, () -> service.crearManual(n));
    }

    @Test
    void crearManualNotificacionTipoEnvioNull() {
        NotificacionModel n = new NotificacionModel();

        n.setMensaje("Hola");

        assertThrows(IllegalArgumentException.class, () -> service.crearManual(n));
    }

    @Test
    void crearManualGmailConUsuario() {
        NotificacionModel n = new NotificacionModel();

        n.setMensaje("Hola");
        n.setTipoEnvio(TipoEnvio.GMAIL);
        n.setIdUsuario(1L);

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setPrimerNombre("Juan");

        when(usuarioClient.obtenerUsuario(1L)).thenReturn(usuario);

        when(factory.obtener(TipoEnvio.GMAIL)).thenReturn(notificacion);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        service.crearManual(n);

        verify(usuarioClient).obtenerUsuario(1L);
    }

    @Test
    void editarNotificacion() {
        NotificacionModel n = new NotificacionModel();

        n.setIdNotificacion(1L);

        when(repository.save(any())).thenReturn(n);

        NotificacionModel resultado = service.editarNotificacion(n);

        assertEquals(1L, resultado.getIdNotificacion());
    }

    @Test
    void editarNotificacionSinId() {
        NotificacionModel n = new NotificacionModel();

        assertThrows(IllegalArgumentException.class, () -> service.editarNotificacion(n));
    }


    @Test
    void crearNotificacionHorarioNull() {
        when(factory.obtener(TipoEnvio.PLATAFORMA)).thenReturn(notificacion);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        NotificacionModel resultado = service.crearDesdeHorario(
                1L,
                1L,
                "Horario creado",
                null);

        assertEquals(TipoEnvio.PLATAFORMA, resultado.getTipoEnvio());
    }

    @Test
    void crearNotificacionPago() {
        when(factory.obtener(TipoEnvio.PLATAFORMA)).thenReturn(notificacion);

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        NotificacionModel resultado = service.crearDesdePago(
                1L,
                1L,
                "Pago generado",
                TipoEnvio.PLATAFORMA);

        assertEquals(1L, resultado.getIdSueldo());
    }

    @Test
    void fallbackManual() {
        NotificacionModel resultado = service.fallbackCrearManual(
                new NotificacionModel(),
                new Exception());

        assertEquals("Servicio de notificaciones no disponible", resultado.getMensaje());
    }

    @Test
    void fallbackHorario() {
        NotificacionModel resultado = service.fallbackCrearDesdeHorario(
                1L,
                1L,
                "hola",
                TipoEnvio.PLATAFORMA,
                new Exception());

        assertEquals("Notificacion pendiente por caida del servicio", resultado.getMensaje());
    }

    @Test
    void fallbackPago() {
        NotificacionModel resultado = service.fallbackCrearDesdePago(
                1L,
                1L,
                "hola",
                TipoEnvio.PLATAFORMA,
                new Exception());

        assertEquals("Notificación de pago pendiente", resultado.getMensaje());
    }
}