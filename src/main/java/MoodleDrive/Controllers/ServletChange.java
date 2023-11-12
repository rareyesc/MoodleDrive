package MoodleDrive.Controllers;

import MoodleDrive.Services.AutenticacionService;
import MoodleDrive.Services.ErrorService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/change")
public class ServletChange {
    private static final Logger logger = LogManager.getLogger(ServletFPass.class);
    @Autowired
    private AutenticacionService autenticacionService;
    @Autowired
    private ErrorService errorService;

    @GetMapping("/password")
    public String mostrarChangePassword(@RequestParam(value = "token", defaultValue = "") String token, Model model, HttpServletResponse response) throws IOException {
        logger.info("Token recibido: " + token);
        if (token.isEmpty() || !autenticacionService.isTokenValid(token)) {
            response.sendRedirect("/login/user");
            return null;
        } else {
            model.addAttribute("token", token);
            return "change";
        }
    }

    @PostMapping("/password")
    public String handlePasswordChange(
            @RequestParam("token") String token,
            @RequestParam("txtPass") String newPassword,
            @RequestParam("txtRePass") String confirmPassword,
            Model model,
            HttpServletResponse response) {
        List<String> errors = errorService.validatePasswordChange(newPassword, confirmPassword);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errorService.prepararErrores(errors));
            return "change";
        }
        if (autenticacionService.cambiarContrasenia(token, newPassword)) {
            autenticacionService.limpiarTokenRecuperacion(token);
            model.addAttribute("passwordChanged", true);
        } else {
            model.addAttribute("passwordChanged", false);
        }
        return "change";
    }
}
