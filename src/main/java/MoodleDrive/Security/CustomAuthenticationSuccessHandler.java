package MoodleDrive.Security;

import java.util.Set;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
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
