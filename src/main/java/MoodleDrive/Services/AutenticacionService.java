package MoodleDrive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import MoodleDrive.Models.Autenticacion;
import MoodleDrive.Repositories.AutenticacionRepository;

@Service
public class AutenticacionService {

    @Autowired
    private AutenticacionRepository autenticacionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void guardarAutenticacion(Autenticacion autenticacion) {
        String contraseniaEncriptada = passwordEncoder.encode(autenticacion.getPass());
        autenticacion.setPass(contraseniaEncriptada);
        autenticacionRepository.save(autenticacion);
    }
}
