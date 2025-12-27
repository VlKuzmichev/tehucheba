package tehucheba.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/manage_quizzes")
public class ManageController {

    @GetMapping()
    public String menu(Model model) {
        return "manage_quizzes";
    }
}
