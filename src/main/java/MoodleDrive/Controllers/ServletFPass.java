package MoodleDrive.Controllers;

import MoodleDrive.Models.Autenticacion;
import MoodleDrive.Models.Perfil;
import MoodleDrive.Repositories.AutenticacionRepository;
import MoodleDrive.Repositories.PerfilRepository;
import MoodleDrive.Services.EmailService;
import MoodleDrive.Services.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/forgot")
public class ServletFPass {
    private static final Logger logger = LogManager.getLogger(ServletFPass.class);
    @Autowired
    private AutenticacionRepository autenticacionRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping("/password")
    public String mostrarForgotPassword(@NotNull Model model) {
        return "forgot";
    }

    @PostMapping("/password")
    public String procesarForgotPassword(@NotNull @RequestParam("email") String email, Model model) {
        if (autenticacionRepository.existsByEmail(email)) {
            String token = tokenService.generarToken();
            LocalDateTime fechaExpiracion = LocalDateTime.now().plusMinutes(30);
            Autenticacion autenticacion = autenticacionRepository.findByEmail(email);
            autenticacion.setTokenRecuperacion(token);
            autenticacion.setFechaExpiracionToken(fechaExpiracion);
            autenticacionRepository.save(autenticacion);
            Optional<Perfil> perfilOptional = perfilRepository.findByAuthId(autenticacion.getIdAuth());
            if (perfilOptional.isPresent()) {
                Perfil perfil = perfilOptional.get();
                String pNombre = perfil.getpNombre();
                String pApellido = perfil.getpApellido();
                emailService.enviarCorreoRecuperacion(email, pNombre, pApellido, token);
                model.addAttribute("envioCorreo", true);
            } else {
                model.addAttribute("perfilNoEncontrado", true);
            }
        } else {
            model.addAttribute("emailNoExiste", true);
        }
        return "forgot";
    }
}
