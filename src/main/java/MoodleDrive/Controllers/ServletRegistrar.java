package MoodleDrive.Controllers;

import MoodleDrive.Models.Perfil;
import MoodleDrive.Models.Tdocumento;
import MoodleDrive.Services.PerfilService;
import MoodleDrive.Services.TdocumentoService;
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

@Controller
@RequestMapping("/registrar")
public class ServletRegistrar {

    @Autowired
    private TdocumentoService tdocumentoService;

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/formulario")
    public String mostrarFormularioRegistro(@NotNull Model model) {
        model.addAttribute("anioFinal", LocalDate.now().getYear() - 10);

        List<Tdocumento> tiposDocumento = tdocumentoService.getAllDocumentTypes();
        model.addAttribute("tiposDocumento", tiposDocumento);

        // Crea un nuevo objeto Perfil para el formulario
        model.addAttribute("perfil", new Perfil());

        return "registrar";
    }

    @PostMapping("/formulario")
    @Transactional
    public String registrar(@ModelAttribute("perfil") @Valid Perfil perfil, BindingResult bindingResult, Model model) {
        // Aquí se ejecutará la validación del lado del servidor
        // Puedes agregar la lógica de validación aquí utilizando las anotaciones de Spring

        // Si hay errores de validación, redirige de nuevo al formulario de registro
        if (bindingResult.hasErrors()) {
            model.addAttribute("anioFinal", LocalDate.now().getYear() - 10);
            List<Tdocumento> tiposDocumento = tdocumentoService.getAllDocumentTypes();
            model.addAttribute("tiposDocumento", tiposDocumento);
            return "registrar";
        }

        // Si no hay errores de validación, guarda el perfil en la base de datos y redirige a una página de registro exitoso
        perfilService.save(perfil);
        return "registroExitoso"; // Asegúrate de crear esta vista
    }
}
