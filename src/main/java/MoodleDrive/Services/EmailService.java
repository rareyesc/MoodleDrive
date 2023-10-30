package MoodleDrive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    public void sendWelcomeEmail(String to, String firstName, String lastName) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Bienvenido a Moodle Drive");
            String header = "<div style='background-color:#4E73DF; padding:10px; width: 30%;'>"
                    + "<h2 style='color:#FFFFFF; font-size: 24px; margin: 0;'>Bienvenido a Moodle Drive</h2>"
                    + "</div>";
            String body = "<div style='background-color:#FFFFFF; padding:9px; border: 1px solid; width: 30%;'>"
                    + "<p style='font-size: 22px;'>Hola " + firstName + " " + lastName + ":</p>"
                    + "<p>Te damos la bienvenida a Moodle Drive. Estamos encantados de tenerte con nosotros.</p>"
                    + "<p style='font-weight:bold; margin-top:20px;'>Esperamos que disfrutes de nuestra plataforma y que encuentres todo lo que necesitas para tu aprendizaje.</p>"
                    + "<p style='font-weight:bold; margin-top:20px;'><span style='font-size:0.9rem;'>No reenvíes este correo electrónico a nadie)</span>.</p>"
                    + "</div>";
            String footer = "<div style='background-color:#4E73DF; padding:10px; width: 30%;'>"
                    + "<p style='color: #FFFFFF; margin:0; font-size: 0.9rem;'>Atentamente,</p>"
                    + "<p style='color: #FFFFFF; margin:0; font-size: 0.9rem;'>El equipo de Moodle Drive.</p>"
                    + "</div>";
            String text = header + body + footer;
            helper.setText(text, true);
            emailSender.send(message);
            logger.info("Correo electronico enviado exitosamente");
        } catch (MessagingException e) {
            logger.error("Error al enviar el correo electronico", e);
        }
    }
}