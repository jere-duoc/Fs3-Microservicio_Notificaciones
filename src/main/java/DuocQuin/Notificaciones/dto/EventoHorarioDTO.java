package DuocQuin.Notificaciones.dto;

import DuocQuin.Notificaciones.model.TipoEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventoHorarioDTO {
    private Long idHorario;
    private Long idUsuario;
    private String mensaje;
    private TipoEnvio tipoEnvio;
}