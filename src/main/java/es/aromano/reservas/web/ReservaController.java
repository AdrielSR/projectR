package es.aromano.reservas.web;

import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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



	@GetMapping(value="/mis-reservas/page/{pageNumber}")
	public String misReservasPaginadas(@PathVariable int pageNumber, Model model){
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 7);
		Page<Reserva> results = reservaService.reservasUsuario(pageRequest);


		int current = results.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, results.getTotalPages());

		model.addAttribute("results", results);
		model.addAttribute("currentIndex", current);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("reservas", results.getContent());
		model.addAttribute("view", "mis-reservas");

		return "index";
	}


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
