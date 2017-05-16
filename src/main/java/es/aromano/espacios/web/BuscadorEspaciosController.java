package es.aromano.espacios.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BuscadorEspaciosController {


    @RequestMapping(value = "/buscador-espacios", method = RequestMethod.GET)
    public String buscarEspacios(Model model){
        model.addAttribute("view", "buscador-espacios");

        return "index";
    }

}
