package es.aromano.edificios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.edificios.service.EdificioService;

@Controller
@RequestMapping("/admin")
public class EdificioController {

	@Autowired
	private EdificioService edificioService;
	
	@RequestMapping(value = "/edificios", method = RequestMethod.GET)
	public String listarEdificiosEmpresa(Model model){
		model.addAttribute("edificios", edificioService.edificiosEmpresaLogada());
		model.addAttribute("view", "admin-edificios");
		
		return "index";
	}
	
}
