package MoodleDrive.Security;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import MoodleDrive.Repositories.AutenticacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AutenticacionRepository autenticacionRepository;
    @Autowired
    public CustomAuthenticationProvider(UserDetailsService userDetailsService, @Lazy PasswordEncoder passwordEncoder, AutenticacionRepository autenticacionRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.autenticacionRepository = autenticacionRepository;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            autenticacionRepository.updateUltimaConexion(email, LocalDateTime.now());
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
