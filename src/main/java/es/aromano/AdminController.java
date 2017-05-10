package es.aromano;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN = "admin/";

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getAdminView(Model model){
        model.addAttribute("view", ADMIN + "administrar");

        return "index";
    }

}
