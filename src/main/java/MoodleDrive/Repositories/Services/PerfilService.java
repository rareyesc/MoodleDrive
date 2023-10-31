package MoodleDrive.Repositories.Services;

import MoodleDrive.Models.Perfil;
import MoodleDrive.DTO.RegistroDTO;
import MoodleDrive.Repositories.AutenticacionRepository;
import org.apache.logging.log4j.Logger;
import MoodleDrive.Models.Autenticacion;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import MoodleDrive.Repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class PerfilService {
    private static final Logger logger = LogManager.getLogger(AutenticacionService.class);
    @Autowired
    private final PerfilRepository perfilRepository;
    @Autowired
    private final AutenticacionRepository autenticacionRepository;
    @Autowired
    public PerfilService(PerfilRepository perfilRepository, AutenticacionRepository autenticacionRepository) {
        this.perfilRepository = perfilRepository;
        this.autenticacionRepository = autenticacionRepository;
    }
    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
        logger.info("Perfil guardado/actualizado para : " + perfil.getpNombre() + " " +perfil.getpApellido());
    }
    public Perfil crearPerfil(RegistroDTO registroDTO, Autenticacion autenticacion) {
        Perfil perfil = new Perfil();
        ErrorService errorService = new ErrorService();
        perfil.setAutenticacion(autenticacion);
        perfil.setnDocumento(registroDTO.getnDocumento());
        perfil.settDocumento(registroDTO.gettDocumento());
        perfil.setpNombre(errorService.formatName(registroDTO.getpNombre()));
        perfil.setsNombre(errorService.formatName(registroDTO.getsNombre()));
        perfil.setpApellido(errorService.formatName(registroDTO.getpApellido()));
        perfil.setsApellido(errorService.formatName(registroDTO.getsApellido()));
        perfil.setdNacimiento(registroDTO.getdNacimiento());
        perfil.setmNacimiento(registroDTO.getmNacimiento());
        perfil.setaNacimiento(registroDTO.getaNacimiento());
        logger.info("Perfil creado para : " + errorService.formatName(registroDTO.getpNombre()) + " " + errorService.formatName(registroDTO.getpApellido()));
        return perfil;
    }
    public Optional<Perfil> obtenerPerfilPorEmail(String email) {
        Autenticacion autenticacion = autenticacionRepository.findByEmail(email);
        if (autenticacion != null) {
            return perfilRepository.findByAuthId(autenticacion.getIdAuth());
        }
        return Optional.empty();
    }
}
