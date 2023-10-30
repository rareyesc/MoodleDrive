package MoodleDrive.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forgot")
public class ServletFPass {
    private static final Logger logger = LogManager.getLogger(ServletFPass.class);

    @GetMapping("/password")
    public String mostrarForgotPassword(@NotNull Model model) {
        return "forgot";
    }
}
