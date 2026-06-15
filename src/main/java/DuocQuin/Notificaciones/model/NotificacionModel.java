package DuocQuin.Notificaciones.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notificaciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_horario")
    private Long idHorario;

    @Column(name = "id_sueldos")
    private Long idSueldo;

    @NotNull(message = "La notificación debe contener un mensaje")
    @Column(name = "mensaje")
    private String mensaje;

    @NotNull(message = "La notificación debe contener una fecha de envio")
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_envio")
    private TipoEnvio tipoEnvio;

    @Column(name = "leida")
    private Boolean leida;
}