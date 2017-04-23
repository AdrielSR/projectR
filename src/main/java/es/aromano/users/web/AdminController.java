package es.aromano.users.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.aromano.users.model.User;
import es.aromano.users.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView getAdminView(){
		ModelAndView model = new ModelAndView("index");
		model.addObject("view", "administrar");
		
		return model;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView listarUsuariosEmpresa(){
		ModelAndView model = new ModelAndView("index");
		model.addObject("users", userService.findUsuariosEmpresa());
		model.addObject("view", "admin-users");
		
		return model;
	}
	
	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public ModelAndView crearUsuariosEmpresa(){
		ModelAndView model = new ModelAndView("index");
		model.addObject("users", userService.findUsuariosEmpresa());
		
		return model;
	}
	
	@RequestMapping(value = "/users/new", method = RequestMethod.POST)
	public String doCrearUsuariosEmpresa(@ModelAttribute User user){
		
		User newUser = userService.createUserEmpresa(user);
		
		if(newUser == null){
			return "/users/new?error";
		}
		
		return "redirect:/users";
	}
	
}
