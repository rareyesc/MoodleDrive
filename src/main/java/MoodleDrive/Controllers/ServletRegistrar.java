package MoodleDrive.Controllers;

import java.util.List;
import java.time.LocalDate;
import MoodleDrive.Models.*;
import MoodleDrive.Repositories.Services.*;
import jakarta.validation.Valid;
import MoodleDrive.DTO.RegistroDTO;
import org.springframework.ui.Model;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import MoodleDrive.Repositories.AsignaRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Anotación que indica que esta clase es un controlador en Spring MVC.
// Anotación que define la ruta base para este controlador.
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
    @Autowired
    private EmailService emailService;
    // Método que maneja las solicitudes GET a la ruta "/formulario" relativa a la ruta base del controlador.
    @GetMapping("/formulario")
    public String mostrarFormularioRegistro(@NotNull Model model) {
        // Añade un atributo al modelo con el año actual menos 10.
        model.addAttribute("anioFinal", LocalDate.now().getYear() - 10);
        // Obtén una lista de tipos de documento y añádela como un atributo al modelo.
        List<Tdocumento> tiposDocumento = tdocumentoService.getAllDocumentTypes();
        model.addAttribute("tiposDocumento", tiposDocumento);
        // Crea un nuevo objeto RegistroDTO y añádelo como un atributo al modelo.
        model.addAttribute("registroDTO", new RegistroDTO());
        // Retorna el nombre de la vista "registrar", lo que le dice a Spring que renderice la vista "registrar".
        return "registrar";
    }
    @PostMapping("/formulario")
    @Transactional
    public String registrar(@ModelAttribute("registroDTO") @Valid RegistroDTO registroDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // Llamada al servicio de error para validar el DTO del registro y obtener una lista de errores.
        List<String> errores = errorService.validateRegistroDTO(registroDTO);
        // Verifica si hay errores en la lista.
        if (!errores.isEmpty()) {
            // Verifica si el error "Usuario ya registrado" está en la lista y, de ser así, añade un atributo al modelo.
            if (errores.contains("Usuario ya registrado")) {
                model.addAttribute("usuarioExistente", true);
            }
            // Verifica si el error relacionado con la contraseña está en la lista y, de ser así, añade un atributo al modelo.
            if (errores.contains("La contraseña no puede estar vacia y debe contener al menos 8 caracteres")) {
                model.addAttribute("contraseniaInvalida", true);
            }
            // Bucle que recorre la lista de errores, añadiendo cada error como un atributo al modelo y mostrando el error en la consola.
            for (int i = 0; i < errores.size(); i++) {
                model.addAttribute("error" + (i + 1), errores.get(i));
                System.out.println("Error " + (i + 1) + ": " + errores.get(i));
            }
            // Obtiene una lista de tipos de documento y añade la lista y el año final como atributos al modelo.
            List<Tdocumento> tiposDocumento = tdocumentoService.getAllDocumentTypes();
            model.addAttribute("tiposDocumento", tiposDocumento);
            model.addAttribute("anioFinal", LocalDate.now().getYear() - 10);
            // Retorna la vista "registrar" para que el usuario pueda corregir los errores.
            return "registrar";
        }
        // Si no hay errores, se procede a procesar el formulario.
        // Crea un objeto Autenticacion y guarda el objeto en la base de datos.
        Autenticacion autenticacion = autenticacionService.crearAutenticacion(registroDTO);
        autenticacionService.guardarAutenticacion(autenticacion);
        // Crea un objeto Perfil y guarda el objeto en la base de datos.
        Perfil perfil = perfilService.crearPerfil(registroDTO, autenticacion);
        perfilService.save(perfil);
        // Obtiene el rol de estudiante y crea un objeto AsignaRol, luego guarda el objeto AsignaRol en la base de datos.
        int idRolEstudiante = rolService.getIdByNombreRol("estudiante");
        Rol rolEstudiante = rolService.getRolById(idRolEstudiante);
        AsignaRol asignaRol = asignaRolService.asignaRol(registroDTO, autenticacion, rolEstudiante);
        asignaRolRepository.save(asignaRol);
        // Añade un atributo flash para indicar que el registro fue exitoso.
        redirectAttributes.addFlashAttribute("registroExitoso", true);
        // Enviar correo electrónico de bienvenida
        emailService.sendWelcomeEmail(autenticacion.getEmail(), perfil.getpNombre(), perfil.getpApellido());
        // Redirecciona al usuario de vuelta al formulario de registro.
        return "redirect:/registrar/formulario";
    }
}