package MoodleDrive.Controllers;

import org.springframework.ui.Model;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.apache.logging.log4j.LogManager;
import MoodleDrive.Repositories.Services.ErrorLoginService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import MoodleDrive.Repositories.Services.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public String mostrarLoginUser(@NotNull Model model) {
        // Retorna el nombre de la vista "login", lo que le dice a Spring que renderice la vista "login".
        return "login";
    }
}
