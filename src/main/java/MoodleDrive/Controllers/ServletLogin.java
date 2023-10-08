package MoodleDrive.Controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class ServletLogin {

    @GetMapping("/user")
    public String mostrarLoginUser(@NotNull Model model) {
        return "login";
    }
}
