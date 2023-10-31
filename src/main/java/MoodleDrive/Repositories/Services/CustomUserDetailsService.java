package MoodleDrive.Repositories.Services;

import java.util.ArrayList;
import java.util.List;

import MoodleDrive.Models.AsignaRol;
import MoodleDrive.Models.Autenticacion;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        List<AsignaRol> asignaRoles = autenticacion.getAsignaRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (AsignaRol asignaRol : asignaRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + asignaRol.getRol().getNombreRol().toUpperCase()));
        }
        return User.withUsername(autenticacion.getEmail())
                .password(autenticacion.getPass())
                .authorities(authorities)
                .build();
    }
}
