package DuocQuin.Notificaciones.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long idUsuario;
    private String primerNombre;
    private String segundoNombre;
    private String correoElectronico;
    private String telefonoCelular;

}
