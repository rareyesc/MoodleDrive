package MoodleDrive.Controllers;

import MoodleDrive.Models.Perfil;
import MoodleDrive.Repositories.Services.PerfilService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
// Anotación @Controller indicando que esta clase es un controlador en el contexto de Spring MVC.
// Anotación @RequestMapping indicando que este controlador manejará todas las rutas que comiencen con "/mainS".
@Controller
@RequestMapping("/mainS")
public class ServletStudent {
    private static final Logger logger = LogManager.getLogger(ServletStudent.class);
    @Autowired
    private PerfilService perfilService;
    // Anotación @GetMapping indicando que el siguiente método manejará las solicitudes GET a la ruta "/student" dentro del prefijo "/mainS".
    // Definición del método mostrarVistaEstudiante que acepta un objeto Model como parámetro (anotado como NotNull, lo que significa que no debe ser nulo).
    @GetMapping("/student")
    public String mostrarVistaEstudiante(@NotNull Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String email = userDetails.getUsername();  // Asumiendo que el email es el username en UserDetails
            Optional<Perfil> perfilOptional = perfilService.obtenerPerfilPorEmail(email);
            if (perfilOptional.isPresent()) {
                Perfil perfil = perfilOptional.get();
                logger.info("Primer Nombre: " + perfil.getpNombre());
                logger.info("Primer Apellido: " + perfil.getpApellido());
                model.addAttribute("primerNombre", perfil.getpNombre());
                model.addAttribute("primerApellido", perfil.getpApellido());
            } else {
                logger.warn("No se encontró el perfil para el usuario: " + userDetails.getUsername());
            }
        } else {
            logger.warn("UserDetails es null");
        }
        return "mainS";
    }
}
