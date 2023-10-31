package MoodleDrive.Repositories.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService {

    public String generarToken() {
        return UUID.randomUUID().toString();
    }

    public LocalDateTime generarFechaExpiracion() {
        return LocalDateTime.now().plusMinutes(30);
    }
}