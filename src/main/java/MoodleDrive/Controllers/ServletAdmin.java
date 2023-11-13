package MoodleDrive.Controllers;


import MoodleDrive.DTO.UsuarioDTO;
import MoodleDrive.Models.Perfil;
import MoodleDrive.Repositories.UsuarioRepository;
import MoodleDrive.Services.PerfilService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mainA")
public class ServletAdmin {
    private static final Logger logger = LogManager.getLogger(ServletAdmin.class);

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/admin")
    public String mostrarVistaAdmin(@ModelAttribute("UsuarioDTO") @Valid UsuarioDTO usuarioDTO, @NotNull Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String email = userDetails.getUsername();  // Asumiendo que el email es el username en UserDetails
            Optional<Perfil> perfilOptional = perfilService.obtenerPerfilPorEmail(email);
            if (perfilOptional.isPresent()) {
                Perfil perfil = perfilOptional.get();
                logger.info("Primer Nombre: " + perfil.getpNombre());
                logger.info("Primer Apellido: " + perfil.getpApellido());
                model.addAttribute("primerNombre", perfil.getpNombre());
                model.addAttribute("primerApellido", perfil.getpApellido());

                //Datos para listas los usuarios
                List<UsuarioDTO> usuarios = usuarioRepository.findAllUsuarios();
                logger.info("UsuariosDTO: " + usuarios);
                model.addAttribute("usuarios", usuarios);



            } else {
                logger.warn("No se encontró el perfil para el usuario: " + userDetails.getUsername());
            }
        } else {
            logger.warn("UserDetails es null");
        }
        return "mainA";
    }

}

