package es.aromano;

import es.aromano.reservas.domain.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.reservas.service.ReservaService;

@Controller
public class HomeController {

	@Autowired
	private ReservaService reservaService;

    @GetMapping({"/", "/mis-reservas"})
    public String misReservas(){
		return "redirect:/mis-reservas/page/1";
    }


}
