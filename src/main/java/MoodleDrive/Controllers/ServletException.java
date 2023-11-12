package MoodleDrive.Controllers;

import MoodleDrive.Services.ErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice es una anotación especial en Spring que marca esta clase como un controlador de excepciones global.
// Esto significa que cualquier excepción lanzada por los controladores de la aplicación será manejada por esta clase.
@ControllerAdvice
public class ServletException {

    // @Autowired es una anotación de Spring que inyecta automáticamente una instancia de ErrorService en este campo.
    @Autowired
    private ErrorService errorService;

    // @ExceptionHandler es una anotación de Spring que marca este método como un manejador de excepciones para la clase Exception.
    // Esto significa que si alguna excepción de tipo Exception (o cualquier subclase de Exception) es lanzada por un controlador,
    // este método será invocado para manejarla.
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        return null;
    }
}
