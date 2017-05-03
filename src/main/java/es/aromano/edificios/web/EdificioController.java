package es.aromano.edificios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.edificios.service.EdificioService;
import es.aromano.espacios.service.EspacioService;

@Controller
public class EdificioController {

	@Autowired
	private EdificioService edificioService;
	
	@Autowired
	private EspacioService espacioService;
	
	@RequestMapping(value = "/edificios", method = RequestMethod.GET)
	public String listarEdificiosActivos(Model model){
		model.addAttribute("edificios", edificioService.edificiosActivos());
		model.addAttribute("view", "reser-select-edificio");
		
		return "index";
	}
	
	@PreAuthorize("@edificioServiceImpl.canAccessUser(#idEdificio)")
	@RequestMapping(value = "/edificio/{id}/espacios", method = RequestMethod.GET)
	public String listarEspaciosActivosDeEdificio(@PathVariable("id") int idEdificio, Model model){
		model.addAttribute("espacios", espacioService.findEspaciosActivosByIdEdificio(idEdificio));
		model.addAttribute("view", "reser-select-espacio");
		
		return "index";
	}
	
}
