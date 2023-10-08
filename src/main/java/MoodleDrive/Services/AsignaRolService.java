package MoodleDrive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MoodleDrive.Models.AsignaRol;
import MoodleDrive.Repositories.AsignaRolRepository;

@Service
public class AsignaRolService {

    @Autowired
    private AsignaRolRepository asignaRolRepository;

}
