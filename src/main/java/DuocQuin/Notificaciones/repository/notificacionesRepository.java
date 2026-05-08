package DuocQuin.Notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DuocQuin.Notificaciones.model.NotificacionModel;

public interface NotificacionesRepository extends JpaRepository<NotificacionModel, Long>{
}
