package MoodleDrive.Repositories.Services;

import java.util.*;
import MoodleDrive.DTO.RegistroDTO;
import MoodleDrive.Models.Tdocumento;
import org.springframework.stereotype.Service;
import MoodleDrive.Repositories.PerfilRepository;
import MoodleDrive.Repositories.AutenticacionRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ErrorService {
    @Autowired
    private AutenticacionRepository autenticacionRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private TdocumentoService tdocumentoService;
    public List<String> validateRegistroDTO(RegistroDTO registroDTO) {
        List<String> errors = new ArrayList<>();
        if (emailYaRegistrado(registroDTO.getEmail()) || cedulaYaRegistrada(registroDTO.getnDocumento())) {
            errors.add("Usuario ya registrado");
        }
        if (!validarNumeroDocumento(registroDTO.getnDocumento())) {
            errors.add("Numero de documento invalido o debe ser numerico");
        }
        if (!validarTipoDocumento(registroDTO.gettDocumento().getIdtDocumento())) {
            errors.add("Tipo de documento invalido");
        }
        if (!validarNombresOApellidos(registroDTO.getpNombre())) {
            errors.add("El primer nombre no puede estar vacio o debe ser solo letras");
        }
        if (!validarNombresOApellidos(registroDTO.getpApellido())) {
            errors.add("El primer apellido no puede estar vacio o debe ser solo letras");
        }
        if (!validarEmail(registroDTO.getEmail())) {
            errors.add("El correo no puede estar vacio y debe ser dominio @sanmateo.edu.co");
        }
        if (!validarFechaNacimiento(registroDTO.getdNacimiento(), registroDTO.getmNacimiento(), registroDTO.getaNacimiento())) {
            errors.add("Dia de nacimiento, mes de nacimiento o año de nacimiento invalido");
        }
        if (!validarContrasenia(registroDTO.getPass())) {
            errors.add("La contraseña no puede estar vacia y debe contener al menos 8 caracteres");
        }
        if (!validarContraseniasIguales(registroDTO.getPass(), registroDTO.getConfirmPassword())) {
            errors.add("Las contraseñas no coinciden");
        }
        return errors;
    }
    public Map<String, String> prepararErrores(List<String> errores) {
        Map<String, String> mapaErrores = new HashMap<>();
        for (int i = 0; i < errores.size(); i++) {
            mapaErrores.put("error" + (i + 1), errores.get(i));
        }
        return mapaErrores;
    }
    private boolean validarNumeroDocumento(String nDoc) {
        try {
            int numeroDocumento = Integer.parseInt(nDoc);
            return numeroDocumento >= 10000;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean validarTipoDocumento(int tipoDocumento) {
        List<Tdocumento> tiposDocumento = tdocumentoService.getAllDocumentTypes();
        return tiposDocumento.stream().anyMatch(tipo -> tipo.getIdtDocumento() == tipoDocumento);
    }
    private boolean validarNombresOApellidos(String campo) {
        return campo != null && !campo.isEmpty() && campo.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,15}$");
    }
    public String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
    private boolean validarEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@sanmateo\\.edu\\.co$");
    }
    private boolean validarFechaNacimiento(int dia, int mes, int anio) {
        try {
            Calendar current = Calendar.getInstance();
            int currentYear = current.get(Calendar.YEAR);
            if (anio < 1950 || anio > currentYear) {
                return false;
            }
            Calendar birthDate = Calendar.getInstance();
            birthDate.set(anio, mes, dia);
            return !birthDate.after(current);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean validarContrasenia(String contrasenia) {
        return contrasenia != null && contrasenia.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])(?=\\S+$).{8,}$");
    }
    private boolean validarContraseniasIguales(String pass, String confirmPassword) {
        return pass != null && pass.equals(confirmPassword);
    }
    public boolean emailYaRegistrado(String email) {
        return autenticacionRepository.existsByEmail(email);
    }
    public boolean cedulaYaRegistrada(String nDocumento) {
        return perfilRepository.existsBynDocumento(nDocumento);
    }
    public List<String> validatePasswordChange(String newPassword, String confirmPassword) {
        List<String> errors = new ArrayList<>();
        if (!validarContrasenia(newPassword)) {
            errors.add("La contrasena no cumple con los requisitos mínimos de seguridad.");
        }
        if (!validarContraseniasIguales(newPassword, confirmPassword)) {
            errors.add("Las contraseñas no coinciden.");
        }
        return errors;
    }
}
