package DuocQuin.Notificaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import DuocQuin.Notificaciones.model.NotificacionModel;
import DuocQuin.Notificaciones.service.NotificacionesService;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService;

    //Crear notificacion de forma manual
    @PostMapping("/crear-individual")
    public NotificacionModel crearNotificacion(
            @RequestBody NotificacionModel notificacionModel) {

        return notificacionesService.crearManual(notificacionModel);
    }

    //Listar notificaciones
    @GetMapping
    public List<NotificacionModel> listar() {
        return notificacionesService.listarNotificaciones();
    }

    //Eliminar notificacion(OPCIONAL)
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        notificacionesService.eliminarNotificacion(id);
        return "Notificacion eliminada correctamente";
    }

    //Editar notificacion (OPCIONAL)
    @PutMapping("/editar")
    public NotificacionModel editar(
            @RequestBody NotificacionModel notificacionModel) {

        return notificacionesService.editarNotificacion(notificacionModel);
    }
}