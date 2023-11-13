package MoodleDrive.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.ui.Model;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.apache.logging.log4j.LogManager;
import MoodleDrive.Services.ErrorLoginService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import MoodleDrive.Services.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Set;

// Anotación @Controller que indica a Spring que esta clase es un controlador.
// Anotación @RequestMapping que especifica la ruta base para este controlador.
@Controller
@RequestMapping("/login")
public class ServletLogin {

    // Anotación @Autowired que le dice a Spring que inyecte una instancia de AutenticacionService en este campo.
    @Autowired
    private AutenticacionService autenticacionService;

    // Anotación @Autowired que le dice a Spring que inyecte una instancia de ErrorLoginService en este campo.
    @Autowired
    private ErrorLoginService errorLoginService;

    // Declaración y asignación de un logger para esta clase, usando la biblioteca log4j.
    private static final Logger logger = LogManager.getLogger(ServletLogin.class);

    // Anotación @GetMapping que especifica que este método debería manejar solicitudes GET a la ruta "/user" relativa a la ruta base del controlador.
    // Método que será invocado cuando se reciba una solicitud GET a la ruta especificada.
    // El parámetro Model es proporcionado por Spring y puede ser usado para pasar datos a la vista.
    @GetMapping("/user")
    public String mostrarLoginUser(@NotNull Model model, HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (authentication != null && authentication.isAuthenticated()) {
            // Redirigir a los usuarios autenticados a su página principal basada en roles
            return redirigirPorRol(authentication, response);
        }
        // Retorna el nombre de la vista "login", lo que le dice a Spring que renderice la vista "login".
        return "login";
    }

    private String redirigirPorRol(Authentication authentication, HttpServletResponse response) throws IOException {
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
        return null;
    }
}
