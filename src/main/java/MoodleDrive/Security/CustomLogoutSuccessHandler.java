package MoodleDrive.Security;

import MoodleDrive.Models.Autenticacion;
import MoodleDrive.Models.Sesion;
import MoodleDrive.Repositories.AutenticacionRepository;
import MoodleDrive.Repositories.SesionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private AutenticacionRepository autenticacionRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        if (authentication != null && authentication.getDetails() != null) {
            String email = authentication.getName();
            Autenticacion usuario = autenticacionRepository.findByEmail(email);
            request.getSession().removeAttribute("errorType");
            response.setStatus(HttpServletResponse.SC_OK);
            Optional<Sesion> sesionActivaOpt = sesionRepository.findUltimaSesionActiva(usuario.getIdAuth());
            if (sesionActivaOpt.isPresent()) {
                Sesion sesionActiva = sesionActivaOpt.get();
                sesionActiva.setFechaCierre(LocalDateTime.now());
                sesionActiva.setEstado(0);
                sesionRepository.save(sesionActiva);
            }
            response.sendRedirect("/login/user");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/login/user");
        }
    }
}