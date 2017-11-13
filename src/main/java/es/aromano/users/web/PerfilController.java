package es.aromano.users.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String verPerfil(Model model){
        model.addAttribute("view", "perfil");

        return "index";
    }



}
