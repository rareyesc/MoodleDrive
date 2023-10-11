package MoodleDrive.Controllers;

import MoodleDrive.DTO.RegistroDTO;
import MoodleDrive.Models.*;
import MoodleDrive.Repositories.AsignaRolRepository;
import MoodleDrive.Services.*;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registrar")
public class ServletRegistrar {

    @Autowired
    private TdocumentoService tdocumentoService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private AutenticacionService autenticacionService;

    @Autowired
    private RolService rolService;

    @Autowired
    private AsignaRolService asignaRolService;

    @Autowired
    private AsignaRolRepository asignaRolRepository;

    @Autowired
    private ErrorService errorService;

    @GetMapping("/formulario")
    public String mostrarFormularioRegistro(@NotNull Model model) {
        model.addAttribute("anioFinal", LocalDate.now().getYear() - 10);

        List<Tdocumento> tiposDocumento = tdocumentoService.getAllDocumentTypes();
        model.addAttribute("tiposDocumento", tiposDocumento);

        // Crea un nuevo objeto RegistroDTO para el formulario
        model.addAttribute("registroDTO", new RegistroDTO());

        return "registrar";
    }

    @PostMapping("/formulario")
    @Transactional
    public String registrar(@ModelAttribute("registroDTO") @Valid RegistroDTO registroDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        List<String> errores = errorService.validateRegistroDTO(registroDTO);

        if (!errores.isEmpty()) {
            if (errores.contains("Usuario ya registrado")) {
                model.addAttribute("usuarioExistente", true);
            }
            if (errores.contains("La contraseña no puede estar vacia y debe contener al menos 8 caracteres")) {
                model.addAttribute("contraseniaInvalida", true);
            }
            for (int i = 0; i < errores.size(); i++) {
                model.addAttribute("error" + (i + 1), errores.get(i));
                System.out.println("Error " + (i + 1) + ": " + errores.get(i));
            }
            List<Tdocumento> tiposDocumento = tdocumentoService.getAllDocumentTypes();
            model.addAttribute("tiposDocumento", tiposDocumento);
            model.addAttribute("anioFinal", LocalDate.now().getYear() - 10);
            return "registrar";
        }

        Autenticacion autenticacion = autenticacionService.crearAutenticacion(registroDTO);
        autenticacionService.guardarAutenticacion(autenticacion);

        Perfil perfil = perfilService.crearPerfil(registroDTO, autenticacion);
        perfilService.save(perfil);

        int idRolEstudiante = rolService.getIdByNombreRol("estudiante");
        Rol rolEstudiante = rolService.getRolById(idRolEstudiante);
        AsignaRol asignaRol = asignaRolService.asignaRol(registroDTO, autenticacion, rolEstudiante);
        asignaRolRepository.save(asignaRol);

        redirectAttributes.addFlashAttribute("registroExitoso", true);
        return "redirect:/registrar/formulario";
    }
}