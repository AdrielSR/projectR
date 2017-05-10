package es.aromano.espacios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.edificios.service.EdificioService;
import es.aromano.espacios.domain.model.Espacio;
import es.aromano.espacios.service.EspacioService;

@Controller
@RequestMapping("/admin")
public class AdminEspacioController {

	private static final String ADMIN = "admin/";

	@Autowired
	private EspacioService espacioService;
	
	@Autowired
	private EdificioService edificioService;
	
	////// Listar espacios //////
	
	@RequestMapping(value = "/espacios", method = RequestMethod.GET)
	public String listarEspaciosActivos(Model model){
		model.addAttribute("espacios", espacioService.espaciosActivos());
		model.addAttribute("view", generateView("admin-espacios"));
		
		return "index";
	}
	
	@RequestMapping(value = "/espacios-no-activos", method = RequestMethod.GET)
	public String listarEspaciosNoActivos(Model model){
		model.addAttribute("espacios_no_activos", espacioService.espaciosDesactivos());
		model.addAttribute("view", generateView("admin-espacios-no-activos"));
		
		return "index";
	}
	
	
	////// Crear espacio //////
	
	@RequestMapping(value = "/espacio", method = RequestMethod.GET)
	public String crearEspacio(Model model){
		model.addAttribute("edificios", edificioService.edificiosActivos());
		model.addAttribute("view", generateView("admin-espacio-new"));
		
		return "index";
	}
	
	@RequestMapping(value = "/espacio", method = RequestMethod.POST)
	public String doCrearEspacio(@ModelAttribute EspacioDTO espacioDTO){
		
		Espacio newEspacio = espacioService.crearEspacio(espacioDTO);
		
		if(newEspacio == null){
			return "/espacio?error";
		}
		
		return "redirect:/admin/espacios";
	}

	private String generateView(String viewName){
		return new StringBuilder(ADMIN).append(viewName).toString();
	}

}
