package es.aromano.espacios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.edificios.service.EdificioService;

@Controller
public class BuscadorEspaciosController {

	@Autowired
	private EdificioService edificioService;

    @RequestMapping(value = "/buscador-espacios", method = RequestMethod.GET)
    public String buscarEspacios(Model model){
    	model.addAttribute("edificios", edificioService.edificiosActivos());
        model.addAttribute("view", "buscador-espacios");

        return "index";
    }

}
