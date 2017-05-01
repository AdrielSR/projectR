package es.aromano.espacios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.espacios.service.EspacioService;

@Controller
@RequestMapping("/admin")
public class AdminEspacioController {

	@Autowired
	private EspacioService espacioService;
	
	////// Listar espacios //////
	
	@RequestMapping(value = "/espacios", method = RequestMethod.GET)
	public String listarEspaciosActivos(Model model){
		model.addAttribute("espacios", espacioService.espaciosActivos());
		model.addAttribute("view", "admin-espacios");
		
		return "index";
	}
	
	
}
