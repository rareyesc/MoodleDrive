package MoodleDrive.DTO;

import MoodleDrive.Models.Tdocumento;
import jakarta.validation.constraints.*;

public class RegistroDTO {

    @NotNull(message = "El email no puede estar vacío")
    @Email(message = "Email no válido")
    @Size(max = 100, message = "El email es demasiado largo")
    private String email;

    @NotNull(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    private String pass;

    private String confirmPassword;

    @NotNull(message = "El número de documento no puede estar vacío")
    @NotBlank
    @Size(max = 40, message = "El número de documento es demasiado largo")
    private String nDocumento;

    @NotNull(message = "El tipo de documento no puede estar vacío")
    private Tdocumento tDocumento;

    @NotNull(message = "El primer nombre no puede estar vacío")
    @NotBlank
    @Size(max = 20, message = "El primer nombre es demasiado largo")
    private String pNombre;

    @Size(max = 20, message = "El segundo nombre es demasiado largo")
    private String sNombre;

    @NotNull(message = "El primer apellido no puede estar vacío")
    @NotBlank
    @Size(max = 20, message = "El primer apellido es demasiado largo")
    private String pApellido;

    @Size(max = 20, message = "El segundo apellido es demasiado largo")
    private String sApellido;

    @NotNull(message = "El día de nacimiento no puede estar vacío")
    @Min(value = 1, message = "El día de nacimiento no puede ser menor que 1")
    @Max(value = 31, message = "El día de nacimiento no puede ser mayor que 31")
    private Integer dNacimiento;

    @NotNull(message = "El mes de nacimiento no puede estar vacío")
    @Min(value = 1, message = "El mes de nacimiento no puede ser menor que 1")
    @Max(value = 12, message = "El mes de nacimiento no puede ser mayor que 12")
    private Integer mNacimiento;

    @NotNull(message = "El año de nacimiento no puede estar vacío")
    @Min(value = 1950, message = "El año de nacimiento no puede ser menor que 1950")
    private Integer aNacimiento;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public Tdocumento gettDocumento() {
        return tDocumento;
    }

    public void settDocumento(Tdocumento tDocumento) {
        this.tDocumento = tDocumento;
    }

    public String getpNombre() {
        return pNombre;
    }

    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public Integer getdNacimiento() {
        return dNacimiento;
    }

    public void setdNacimiento(Integer dNacimiento) {
        this.dNacimiento = dNacimiento;
    }

    public Integer getmNacimiento() {
        return mNacimiento;
    }

    public void setmNacimiento(Integer mNacimiento) {
        this.mNacimiento = mNacimiento;
    }

    public Integer getaNacimiento() {
        return aNacimiento;
    }

    public void setaNacimiento(Integer aNacimiento) {
        this.aNacimiento = aNacimiento;
    }
}
