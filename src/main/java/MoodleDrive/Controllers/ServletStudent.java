package MoodleDrive.Controllers;

import org.springframework.ui.Model;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// Anotación @Controller indicando que esta clase es un controlador en el contexto de Spring MVC.
// Anotación @RequestMapping indicando que este controlador manejará todas las rutas que comiencen con "/mainS".
@Controller
@RequestMapping("/mainS")
public class ServletStudent {
    // Anotación @GetMapping indicando que el siguiente método manejará las solicitudes GET a la ruta "/student" dentro del prefijo "/mainS".
    // Definición del método mostrarVistaEstudiante que acepta un objeto Model como parámetro (anotado como NotNull, lo que significa que no debe ser nulo).
    @GetMapping("/student")
    public String mostrarVistaEstudiante(@NotNull Model model) {
        // Retorna el nombre de la vista "mainS" que será utilizada para renderizar la respuesta.
        return "mainS";
    }
}
