package MoodleDrive.Security;

import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger logger = LogManager.getLogger(CustomAuthenticationFailureHandler.class);
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException {
        logger.error("Fallo en la autenticación: ", exception);
        String errorType;
        if (exception instanceof BadCredentialsException) {
            errorType = "invalid_credentials";
        } else if (exception instanceof DisabledUserException) {
            errorType = "user_disabled";
        } else {
            errorType = "unknown_error";
        }
        request.getSession().setAttribute("errorType", errorType);
        response.sendRedirect("/login/user?error=true");
    }
}
