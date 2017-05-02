package es.aromano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.reservas.service.ReservaService;

@Controller
public class HomeController {

	@Autowired
	private ReservaService reservaService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(Model model){
    	model.addAttribute("reservas", reservaService.reservasUsuario());
    	model.addAttribute("view", "mis-reservas");
    	
    	return "index";
    }


}
