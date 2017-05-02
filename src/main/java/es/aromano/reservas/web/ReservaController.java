package es.aromano.reservas.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservaController {

	
	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public String reservas(Model model){
		model.addAttribute("view", "prueba-reservas");
		
		return "index";
	}
	
}
