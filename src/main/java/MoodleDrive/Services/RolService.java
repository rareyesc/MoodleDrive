package MoodleDrive.Services;

import MoodleDrive.Models.Rol;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import MoodleDrive.Repositories.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RolService {
    private static final Logger logger = LogManager.getLogger(AutenticacionService.class);
    @Autowired
    private RolRepository rolRepository;
    public Integer getIdByNombreRol(String nombreRol) {
        logger.info("Buscando id del rol con nombre de rol : " + nombreRol);
        return rolRepository.findIdByNombreRol(nombreRol);
    }
    public Rol getRolById(Integer id) {
        logger.info("Buscando nombre del rol por id : " + id);
        return rolRepository.findById(id).orElse(null);
    }
}
