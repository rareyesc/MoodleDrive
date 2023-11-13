package MoodleDrive.DTO;

public class UsuarioDTO {
    private int idAuth;
    private String nombre;
    private String apellido;
    private String email;
    private String tipoDocumento;
    private String nDocumento;
    private int aNacimiento;
    private String nombreRol;
    private int estado;

    public UsuarioDTO() {

    }

    public UsuarioDTO(int idAuth, String nombre, String apellido, String email, String tipoDocumento, String nDocumento, int aNacimiento, String nombreRol, int estado) {
        this.idAuth = idAuth;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.tipoDocumento = tipoDocumento;
        this.nDocumento = nDocumento;
        this.aNacimiento = aNacimiento;
        this.nombreRol = nombreRol;
        this.estado = estado;
    }

    //Getter and Setters
    public int getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public int getaNacimiento() {
        return aNacimiento;
    }

    public void setaNacimiento(int aNacimiento) {
        this.aNacimiento = aNacimiento;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
