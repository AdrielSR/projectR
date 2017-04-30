package es.aromano.users.web;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.aromano.users.exceptions.UserException;
import es.aromano.users.model.Role;
import es.aromano.users.model.User;
import es.aromano.users.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	private UserService userService;
	

	////// Listar usuarios //////

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listarUsuariosActivosEmpresa(Model model){
		model.addAttribute("active_users", userService.findUsuariosActivosEmpresaLogada());
		model.addAttribute("view", "admin-users");
		
		return "index";
	}
	
	@RequestMapping(value = "/users-no-actives", method = RequestMethod.GET)
	public String listarUsuariosNoActivosEmpresa(Model model){
		model.addAttribute("no_active_users", userService.findUsuariosDesactivosEmpresaLogada());
		model.addAttribute("view", "admin-users-no-actives");
		
		return "index";
	}

	////// Editar usuario //////

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String editarUsuarioEmpresa(@PathVariable("id") int idUsuario, Model model){
		model.addAttribute("user", userService.findUsuarioEmpresaLogada(idUsuario));
		model.addAttribute("roles", userService.findAllRoles());
		model.addAttribute("view", "admin-user-edit");
		
		return "index";
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public String doEditarUsuarioEmpresa(@PathVariable("id") int idUsuario,
										 @RequestParam(value = "roles",required = false) Set<Role> roles,
										 @ModelAttribute User editedUser, Model model) throws UserException {
		
		editedUser.setRoles(roles);
		
		if(userService.editUser(idUsuario, editedUser) == null){
			return String.format("redirect:/admin/user/%d", idUsuario);
		}
		
		return "redirect:/admin/users";
	}

	////// Crear usuario //////

	@RequestMapping(value = "/user/new", method = RequestMethod.GET)
	public String crearUsuariosEmpresa(Model model){
		model.addAttribute("view", "admin-user-new");
		
		return "index";
	}
	
	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String doCrearUsuariosEmpresa(@ModelAttribute User user){
		
		User newUser = userService.createUser(user);
		
		if(newUser == null){
			return "/user/new?error";
		}
		
		return "redirect:/admin/users";
	}
	
}
