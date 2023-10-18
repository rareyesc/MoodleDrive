package MoodleDrive.DTO;

import MoodleDrive.Models.Tdocumento;
import jakarta.validation.constraints.*;

public class RegistroDTO {
    // Anotaciones de validación para el campo email: no puede ser null, debe ser un email válido y tiene una longitud máxima de 100 caracteres.
    @NotNull(message = "El email no puede estar vacío")
    @Email(message = "Email no válido")
    @Size(max = 100, message = "El email es demasiado largo")
    private String email;
    // Anotaciones de validación para el campo pass: no puede ser null y debe tener una longitud entre 8 y 100 caracteres.
    @NotNull(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    private String pass;
    // Definición del campo confirmPassword de tipo String para confirmar la contraseña.
    private String confirmPassword;
    // Anotaciones de validación para el campo nDocumento: no puede ser null, no puede estar en blanco y tiene una longitud máxima de 40 caracteres.
    @NotNull(message = "El número de documento no puede estar vacío")
    @NotBlank
    @Size(max = 40, message = "El número de documento es demasiado largo")
    private String nDocumento;
    // Anotación de validación para el campo tDocumento: no puede ser null.
    @NotNull(message = "El tipo de documento no puede estar vacío")
    private Tdocumento tDocumento;
    // Validaciones para el campo pNombre: no puede ser null, no puede estar en blanco y su longitud máxima es 20 caracteres.
    @NotNull(message = "El primer nombre no puede estar vacío")
    @NotBlank
    @Size(max = 20, message = "El primer nombre es demasiado largo")
    private String pNombre;
    // Validación para el campo sNombre: su longitud máxima es 20 caracteres.
    @Size(max = 20, message = "El segundo nombre es demasiado largo")
    private String sNombre;
    // Validaciones para el campo pApellido: no puede ser null, no puede estar en blanco y su longitud máxima es 20 caracteres.
    @NotNull(message = "El primer apellido no puede estar vacío")
    @NotBlank
    @Size(max = 20, message = "El primer apellido es demasiado largo")
    private String pApellido;
    // Validación para el campo sApellido: su longitud máxima es 20 caracteres.
    @Size(max = 20, message = "El segundo apellido es demasiado largo")
    private String sApellido;
    // Validaciones para el campo dNacimiento: no puede ser null y su valor debe estar entre 1 y 31.
    @NotNull(message = "El día de nacimiento no puede estar vacío")
    @Min(value = 1, message = "El día de nacimiento no puede ser menor que 1")
    @Max(value = 31, message = "El día de nacimiento no puede ser mayor que 31")
    private Integer dNacimiento;
    // Validaciones para el campo mNacimiento: no puede ser null y su valor debe estar entre 1 y 12.
    @NotNull(message = "El mes de nacimiento no puede estar vacío")
    @Min(value = 1, message = "El mes de nacimiento no puede ser menor que 1")
    @Max(value = 12, message = "El mes de nacimiento no puede ser mayor que 12")
    private Integer mNacimiento;
    // Validaciones para el campo aNacimiento: no puede ser null y su valor no puede ser menor que 1950.
    @NotNull(message = "El año de nacimiento no puede estar vacío")
    @Min(value = 1950, message = "El año de nacimiento no puede ser menor que 1950")
    private Integer aNacimiento;
    // Método getter para el campo email: retorna el valor del campo email.
    public String getEmail() {
        return email;
    }
    // Método setter para el campo email: establece el valor del campo email.
    public void setEmail(String email) {
        this.email = email;
    }
    // Método getter para el campo pass: retorna el valor del campo pass.
    public String getPass() {
        return pass;
    }
    // Método setter para el campo pass: establece el valor del campo pass.
    public void setPass(String pass) {
        this.pass = pass;
    }
    // Método getter para el campo confirmPassword: retorna el valor del campo confirmPassword.
    public String getConfirmPassword() {
        return confirmPassword;
    }
    // Método setter para el campo confirmPassword: establece el valor del campo confirmPassword.
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    // Método getter para el campo nDocumento: retorna el valor del campo nDocumento.
    public String getnDocumento() {
        return nDocumento;
    }
    // Método setter para el campo nDocumento: establece el valor del campo nDocumento.
    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }
    // Método getter para el campo tDocumento: retorna el valor del campo tDocumento.
    public Tdocumento gettDocumento() {
        return tDocumento;
    }
    // Método setter para el campo tDocumento: establece el valor del campo tDocumento.
    public void settDocumento(Tdocumento tDocumento) {
        this.tDocumento = tDocumento;
    }
    // Método getter para el campo pNombre: retorna el valor del campo pNombre.
    public String getpNombre() {
        return pNombre;
    }
    // Método setter para el campo pNombre: establece el valor del campo pNombre.
    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }
    // Método getter para el campo sNombre: retorna el valor del campo sNombre.
    public String getsNombre() {
        return sNombre;
    }
    // Método getter para el campo sNombre: retorna el valor del campo sNombre.
    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }
    // Método getter para el campo pApellido: retorna el valor del campo pApellido.
    public String getpApellido() {
        return pApellido;
    }
    // Método setter para el campo pApellido: establece el valor del campo pApellido.
    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }
    // Método getter para el campo sApellido: retorna el valor del campo sApellido.
    public String getsApellido() {
        return sApellido;
    }
    // Método setter para el campo sApellido: establece el valor del campo sApellido.
    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }
    // Método getter para el campo dNacimiento: retorna el valor del campo dNacimiento.
    public Integer getdNacimiento() {
        return dNacimiento;
    }
    // Método setter para el campo dNacimiento: establece el valor del campo dNacimiento.
    public void setdNacimiento(Integer dNacimiento) {
        this.dNacimiento = dNacimiento;
    }
    // Método getter para el campo mNacimiento: retorna el valor del campo mNacimiento.
    public Integer getmNacimiento() {
        return mNacimiento;
    }
    // Método setter para el campo mNacimiento: establece el valor del campo mNacimiento.
    public void setmNacimiento(Integer mNacimiento) {
        this.mNacimiento = mNacimiento;
    }
    // Método getter para el campo aNacimiento: retorna el valor del campo aNacimiento.
    public Integer getaNacimiento() {
        return aNacimiento;
    }
    // Método setter para el campo aNacimiento: establece el valor del campo aNacimiento.
    public void setaNacimiento(Integer aNacimiento) {
        this.aNacimiento = aNacimiento;
    }
}
