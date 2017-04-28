package es.aromano;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getAdminView(Model model){
        model.addAttribute("view", "administrar");

        return "index";
    }

}
