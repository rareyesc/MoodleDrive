package MoodleDrive.Repositories.Services;

import MoodleDrive.Models.Rol;
import MoodleDrive.DTO.RegistroDTO;
import MoodleDrive.Models.AsignaRol;
import org.apache.logging.log4j.Logger;
import MoodleDrive.Models.Autenticacion;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import MoodleDrive.Repositories.AsignaRolRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AsignaRolService {
    private static final Logger logger = LogManager.getLogger(AutenticacionService.class);
    @Autowired
    private AsignaRolRepository asignaRolRepository;
    public AsignaRol asignaRol(RegistroDTO registroDTO, Autenticacion autenticacion, Rol rol) {
        AsignaRol asignarRol = new AsignaRol();
        ErrorService errorService = new ErrorService();
        asignarRol.setAutenticacion(autenticacion);
        asignarRol.setRol(rol);
        logger.info("Rol Asignado para : " + errorService.formatName(registroDTO.getpNombre()) + " " + errorService.formatName(registroDTO.getpApellido()) + ", Rol: " + rol.getNombreRol());
        return asignarRol;
    }
}
