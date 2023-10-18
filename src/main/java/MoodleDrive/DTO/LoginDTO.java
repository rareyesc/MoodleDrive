package MoodleDrive.DTO;

import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {
    // Anotación @NotEmpty que indica que el campo txtEmail no puede estar vacío, y un mensaje de error personalizado se proporciona en caso de que lo esté.
    @NotEmpty(message = "El email no puede estar vacío")
    private String txtEmail;
    // Anotación @NotEmpty que indica que el campo txtPass no puede estar vacío, y un mensaje de error personalizado se proporciona en caso de que lo esté.
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String txtPass;
    // Método público getEmail que retorna el valor del campo txtEmail.
    public String getEmail() {
        return txtEmail;
    }
    // Método público setEmail que acepta una cadena como argumento y establece el valor del campo txtEmail.
    public void setEmail(String email) {
        this.txtEmail = email;
    }
    // Método público getPass que retorna el valor del campo txtPass.
    public String getPass() {
        return txtPass;
    }
    // Método público setPass que acepta una cadena como argumento y establece el valor del campo txtPass.
    public void setPass(String pass) {
        this.txtPass = pass;
    }
}
