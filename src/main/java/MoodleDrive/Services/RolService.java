package MoodleDrive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MoodleDrive.Models.Rol;
import MoodleDrive.Repositories.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;
}
