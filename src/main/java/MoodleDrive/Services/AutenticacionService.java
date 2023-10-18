package MoodleDrive.Services;

import java.time.LocalDateTime;
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
}
