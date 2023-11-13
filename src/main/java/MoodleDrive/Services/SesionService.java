package MoodleDrive.Services;

import MoodleDrive.Repositories.AutenticacionRepository;
import MoodleDrive.Repositories.SesionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SesionService {

    private static final Logger logger = LogManager.getLogger(SesionService.class);

    @Autowired
    private final SesionRepository sesionRepository;

    @Autowired
    private final AutenticacionRepository autenticacionRepository;

    @Autowired
    public SesionService(SesionRepository sesionRepository, AutenticacionRepository autenticacionRepository) {
        this.sesionRepository = sesionRepository;
        this.autenticacionRepository = autenticacionRepository;
    }

}
