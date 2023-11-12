package MoodleDrive.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import MoodleDrive.DTO.RegistroDTO;
import org.apache.logging.log4j.Logger;
import MoodleDrive.Models.Autenticacion;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import MoodleDrive.Repositories.AutenticacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AutenticacionService {
    private static final Logger logger = LogManager.getLogger(AutenticacionService.class);
    @Autowired
    private AutenticacionRepository autenticacionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void guardarAutenticacion(Autenticacion autenticacion) {
        String contraseniaEncriptada = passwordEncoder.encode(autenticacion.getPass());
        autenticacion.setPass(contraseniaEncriptada);
        autenticacionRepository.save(autenticacion);
        logger.info("Autenticacion guardada con el email: " + autenticacion.getEmail());
    }
    public Autenticacion crearAutenticacion(RegistroDTO registroDTO) {
        Autenticacion autenticacion = new Autenticacion();
        autenticacion.setEmail(registroDTO.getEmail());
        autenticacion.setPass(registroDTO.getPass());
        autenticacion.setFechaRegistro(LocalDateTime.now());
        logger.info("Autenticacion creada con el email: " + autenticacion.getEmail());
        return autenticacion;
    }

    public boolean isTokenValid(String token) {
        Optional<Autenticacion> autenticacionOptional = autenticacionRepository.findByTokenRecuperacion(token);
        if (autenticacionOptional.isPresent()) {
            Autenticacion autenticacion = autenticacionOptional.get();
            return LocalDateTime.now().isBefore(autenticacion.getFechaExpiracionToken());
        }
        return false;
    }

    public boolean cambiarContrasenia(String token, String nuevaContrasenia) {
        Optional<Autenticacion> autenticacionOptional = autenticacionRepository.findByTokenRecuperacion(token);
        if (autenticacionOptional.isPresent()) {
            Autenticacion autenticacion = autenticacionOptional.get();
            String contraseniaEncriptada = passwordEncoder.encode(nuevaContrasenia);
            autenticacion.setPass(contraseniaEncriptada);
            autenticacionRepository.save(autenticacion);
            logger.info("Contrasena actualizada para el email: " + autenticacion.getEmail());
            return true;
        }
        logger.error("Token inválido: no se pudo cambiar la contrasena");
        return false;
    }

    public void limpiarTokenRecuperacion(String token) {
        Optional<Autenticacion> autenticacionOptional = autenticacionRepository.findByTokenRecuperacion(token);
        if (autenticacionOptional.isPresent()) {
            Autenticacion autenticacion = autenticacionOptional.get();
            autenticacion.setTokenRecuperacion(null);
            autenticacion.setFechaExpiracionToken(null);
            autenticacionRepository.save(autenticacion);
            logger.info("Token de recuperacion limpiado para el email: " + autenticacion.getEmail());
        } else {
            logger.warn("No se encontro la autenticacion con el token de recuperacion proporcionado");
        }
    }
}
