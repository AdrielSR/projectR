package es.aromano.users.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.aromano.users.model.User;
import es.aromano.users.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String getAdminView(Model model){
		model.addAttribute("view", "administrar");
		
		return "index";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listarUsuariosEmpresa(Model model){
		model.addAttribute("users", userService.findUsuariosEmpresa());
		model.addAttribute("view", "admin-users");
		
		return "index";
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String editarUsuarioEmpresa(@PathVariable("id") int idUsuario, Model model){
		model.addAttribute("user", userService.findUsuarioEmpresa(idUsuario));
		model.addAttribute("view", "admin-user-edit");
		
		return "index";
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public String doEditarUsuarioEmpresa(@PathVariable("id") int idUsuario, 
												@ModelAttribute User userEdited, Model model){
	
		User currentUser = userService.findUsuarioEmpresa(idUsuario);
		
		
		return "redirect:/admin/users";
	}
	
	
	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public String crearUsuariosEmpresa(Model model){
		model.addAttribute("view", "admin-user-new");
		
		return "index";
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
