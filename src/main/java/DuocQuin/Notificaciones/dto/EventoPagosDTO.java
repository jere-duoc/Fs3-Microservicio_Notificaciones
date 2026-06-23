package DuocQuin.Notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class EventoPagosDTO {

    private Long idSueldo;
    private Long idUsuario;
    private String mensaje;
    private String tipoEnvio;


}



