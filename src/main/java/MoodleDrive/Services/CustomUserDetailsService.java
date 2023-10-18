package MoodleDrive.Services;

import java.util.ArrayList;
import MoodleDrive.Models.Autenticacion;
import org.springframework.stereotype.Service;
import MoodleDrive.Security.DisabledUserException;
import MoodleDrive.Repositories.AutenticacionRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AutenticacionRepository autenticacionRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Autenticacion autenticacion = autenticacionRepository.findByEmail(email);
        if (autenticacion == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }
        if (autenticacion.getEstado() == 0) {
            throw new DisabledUserException("El usuario está deshabilitado");
        }
        return User.withUsername(autenticacion.getEmail())
                .password(autenticacion.getPass())
                .authorities(new ArrayList<>())
                .build();
    }
}
