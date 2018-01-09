package es.aromano.espacios.web;

import es.aromano.espacios.web.dto.EspacioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import es.aromano.edificios.service.EdificioService;
import es.aromano.espacios.domain.model.Espacio;
import es.aromano.espacios.service.EspacioService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminEspacioController {

	private static final String ADMIN = "admin/";

	@Autowired
	private EspacioService espacioService;
	
	@Autowired
	private EdificioService edificioService;
	
	////// Listar espacios //////
	
	@GetMapping(value = "/espacios")
	public String listarEspaciosActivos(Model model){
		model.addAttribute("espacios", espacioService.espaciosActivos());
		model.addAttribute("view", generateView("admin-espacios"));
		
		return "index";
	}
	
	@GetMapping(value = "/espacios-no-activos")
	public String listarEspaciosNoActivos(Model model){
		model.addAttribute("espacios_no_activos", espacioService.espaciosDesactivos());
		model.addAttribute("view", generateView("admin-espacios-no-activos"));
		
		return "index";
	}

	////// Editar espacio //////

	@GetMapping(value = "/espacio/{id}")
	public String editarEspacio(@PathVariable("id") int idEspacio, Model model){
		model.addAttribute("edificios", edificioService.edificiosActivos());
		model.addAttribute("espacio", espacioService.findEspacio(idEspacio));
		model.addAttribute("view", generateView("admin-espacio-edit"));

		return "index";
	}

	
	////// Crear espacio //////
	
	@GetMapping(value = "/espacio")
	public String crearEspacio(Model model, EspacioDTO espacioDTO){
		model.addAttribute("edificios", edificioService.edificiosActivos());
		model.addAttribute("view", generateView("admin-espacio-new"));
		
		return "index";
	}
	
	@PostMapping(value = "/espacio")
	public String doCrearEspacio(Model model, @Valid EspacioDTO espacioDTO, BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			return crearEspacio(model, espacioDTO);
		}

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
