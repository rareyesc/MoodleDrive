package MoodleDrive.Services;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import MoodleDrive.DTO.LoginDTO;
import org.apache.logging.log4j.Logger;
import MoodleDrive.Models.Autenticacion;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import MoodleDrive.Repositories.AutenticacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ErrorLoginService {
    @Autowired
    private AutenticacionRepository autenticacionRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LogManager.getLogger(ErrorLoginService.class);

    public List<String> validateLoginDTO(LoginDTO loginDTO) {
        logger.info("Validando LoginDTO: {}", loginDTO.getEmail());
        List<String> errors = new ArrayList<>();
        if (!emailExiste(loginDTO.getEmail())) {
            errors.add("Usuario no existe");
        }
        if (!usuarioEstaActivo(loginDTO.getEmail())) {
            errors.add("Usuario inactivo");
        }
        if (!contraseniaCorrecta(loginDTO.getEmail(), loginDTO.getPass())) {
            errors.add("Contrasenia incorrecta");
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
    private boolean emailExiste(String email) {
        boolean exists = autenticacionRepository.existsByEmail(email);
        logger.debug("Existe email: {}", exists);
        return exists;
    }
    private boolean usuarioEstaActivo(String email) {
        logger.debug("Verificando si el usuario está activo: {}", email);
        Autenticacion autenticacion = autenticacionRepository.findByEmail(email);
        boolean esActivo = autenticacion != null && autenticacion.getEstado() == 1;
        logger.debug("Usuario activo: {}", esActivo);
        return esActivo;
    }
    private boolean contraseniaCorrecta(String email, String password) {
        logger.debug("Verificando contraseña para el email: {}", email);
        Autenticacion autenticacion = autenticacionRepository.findByEmail(email);
        if (autenticacion != null) {
            boolean match = passwordEncoder.matches(password, autenticacion.getPass());
            logger.debug("Coincidencia de contraseña: {}", match);
            return match;
        }
        logger.warn("No se encontró el usuario con el email: {}", email);
        return false;
    }
}
