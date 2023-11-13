package MoodleDrive.Security;

import java.time.LocalDateTime;
import java.util.Set;
import java.io.IOException;

import MoodleDrive.Models.Autenticacion;
import MoodleDrive.Models.Sesion;
import MoodleDrive.Repositories.AutenticacionRepository;
import MoodleDrive.Repositories.SesionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private AutenticacionRepository autenticacionRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String email = authentication.getName();
        Autenticacion usuario = autenticacionRepository.findByEmail(email);
        Sesion nuevaSesion = new Sesion();
        nuevaSesion.setAutenticacion(usuario);
        nuevaSesion.setFechaCreacion(LocalDateTime.now());
        nuevaSesion.setFechaActualizacion(LocalDateTime.now());
        sesionRepository.save(nuevaSesion);
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/mainA/admin");
        } else if (roles.contains("ROLE_ESTUDIANTE")) {
            response.sendRedirect("/mainS/student");
        } else if (roles.contains("ROLE_PROFESOR")) {
            response.sendRedirect("/mainP/profesor");
        } else if (roles.contains("ROLE_INVITADO")) {
            response.sendRedirect("/mainI/invitado");
        }
    }
}
