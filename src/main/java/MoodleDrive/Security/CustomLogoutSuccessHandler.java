package MoodleDrive.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        if (authentication != null && authentication.getDetails() != null) {
            request.getSession().removeAttribute("errorType");
            response.setStatus(HttpServletResponse.SC_OK);
            // redireccionar al login
            response.sendRedirect("/login/user");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            // redireccionar al login
            response.sendRedirect("/login/user");
        }
    }
}