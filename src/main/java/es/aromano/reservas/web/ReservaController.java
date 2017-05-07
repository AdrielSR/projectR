package es.aromano.reservas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.espacios.service.EspacioService;

@Controller
public class ReservaController {

	@Autowired
	private EspacioService espacioService;
	
	@PreAuthorize("@espacioServiceImpl.canAccessUser(#idEspacio)")
	@RequestMapping(value = "/espacio/{id}/reservas", method = RequestMethod.GET)
	public String reservas(@PathVariable("id") int idEspacio, Model model){
		model.addAttribute("idEspacio", idEspacio);
		model.addAttribute("view", "reservas-calendario");
		
		return "index";
	}
	
}
