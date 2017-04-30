package es.aromano.edificios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.aromano.edificios.model.Edificio;
import es.aromano.edificios.service.EdificioService;

@Controller
@RequestMapping("/admin")
public class AdminEdificioController {

	@Autowired
	private EdificioService edificioService;
	
	////// Listar edificios //////
	
	@RequestMapping(value = "/edificios", method = RequestMethod.GET)
	public String listarEdificiosEmpresa(Model model){
		model.addAttribute("edificios", edificioService.edificiosActivos());
		model.addAttribute("view", "admin-edificios");
		
		return "index";
	}
	
	//////Editar edificio //////

	@RequestMapping(value = "/edificio/{id}", method = RequestMethod.GET)
	public String editarEdificioEmpresa(@PathVariable("id") int idEdificio, Model model){
		model.addAttribute("edificio", edificioService.findEdificio(idEdificio));
		model.addAttribute("view", "admin-edificio-edit");
		
		return "index";
	}
	
	@RequestMapping(value = "/edificio/{id}", method = RequestMethod.POST)
	public String doEditarEdificioEmpresa(@PathVariable("id") int idEdificio,
										 @ModelAttribute Edificio edificio, Model model) {
		
		if(edificioService.editarEdificio(idEdificio, edificio) == null){
			return String.format("redirect:/admin/edificio/%d", idEdificio);
		}
		
		return "redirect:/admin/edificios";
	}
	
	
	////// Crear edificio //////
	
	@RequestMapping(value = "/edificio/new", method = RequestMethod.GET)
	public String crearEdificioEmpresa(Model model){
		model.addAttribute("view", "admin-edificio-new");
		
		return "index";
	}
	
	@RequestMapping(value = "/edificio/new", method = RequestMethod.POST)
	public String doCrearEdificioEmpresa(@ModelAttribute Edificio edificio){
		
		Edificio newEdificio = edificioService.crearEdificio(edificio);
		
		if(newEdificio == null){
			return "/edificio/new?error";
		}
		
		return "redirect:/admin/edificios";
	}
	
}
