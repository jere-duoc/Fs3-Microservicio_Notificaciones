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

@Entity
@Table(name = "notificaciones")
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

    public NotificacionModel() {}

    public NotificacionModel(Long idNotificacion, Long idUsuario, Long idHorario, Long idSueldo, String mensaje, LocalDateTime fechaEnvio, TipoEnvio tipoEnvio, Boolean leida) {
        this.idNotificacion = idNotificacion;
        this.idUsuario = idUsuario;
        this.idHorario = idHorario;
        this.idSueldo = idSueldo;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.tipoEnvio = tipoEnvio;
        this.leida = leida;
    }

    public Long getIdNotificacion() { return idNotificacion; }
    public void setIdNotificacion(Long idNotificacion) { this.idNotificacion = idNotificacion; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Long getIdHorario() { return idHorario; }
    public void setIdHorario(Long idHorario) { this.idHorario = idHorario; }

    public Long getIdSueldo() { return idSueldo; }
    public void setIdSueldo(Long idSueldo) { this.idSueldo = idSueldo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public TipoEnvio getTipoEnvio() { return tipoEnvio; }
    public void setTipoEnvio(TipoEnvio tipoEnvio) { this.tipoEnvio = tipoEnvio; }

    public Boolean getLeida() { return leida; }
    public void setLeida(Boolean leida) { this.leida = leida; }
}