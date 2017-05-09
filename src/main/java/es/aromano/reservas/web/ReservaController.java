package es.aromano.reservas.web;

import es.aromano.reservas.service.ReservaService;
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
	private ReservaService reservaService;

	@Autowired
	private EspacioService espacioService;
	
	@PreAuthorize("@espacioServiceImpl.canAccessUser(#idEspacio)")
	@RequestMapping(value = "/espacio/{id}/reservas", method = RequestMethod.GET)
	public String reservasEspacio(@PathVariable("id") int idEspacio, Model model){
		model.addAttribute("idEspacio", idEspacio);
		model.addAttribute("view", "reservas-calendario");
		
		return "index";
	}

	@PreAuthorize("@reservaServiceImpl.canAccessUser(#idReserva)")
	@RequestMapping(value = "/reserva/{id}", method = RequestMethod.GET)
	public String editarReserva(@PathVariable("id") long idReserva, Model model){
		model.addAttribute("reserva", reservaService.findReservaByIdReserva(idReserva));
		model.addAttribute("view", "reserva-edit");

		return "index";
	}
	
}
