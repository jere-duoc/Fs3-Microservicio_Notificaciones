package DuocQuin.Notificaciones.dto;

public class UsuarioDTO {
    private Long idUsuario;
    private String primerNombre;
    private String segundoNombre;
    private String correoElectronico;
    private String telefonoCelular;

    public UsuarioDTO() {}

    public UsuarioDTO(Long idUsuario, String primerNombre, String segundoNombre, String correoElectronico, String telefonoCelular) {
        this.idUsuario = idUsuario;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.correoElectronico = correoElectronico;
        this.telefonoCelular = telefonoCelular;
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }

    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefonoCelular() { return telefonoCelular; }
    public void setTelefonoCelular(String telefonoCelular) { this.telefonoCelular = telefonoCelular; }
}