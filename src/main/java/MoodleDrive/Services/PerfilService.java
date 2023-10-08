package MoodleDrive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MoodleDrive.Models.Perfil;
import MoodleDrive.Repositories.PerfilRepository;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
    }

}
