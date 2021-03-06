package es.aromano.users.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.aromano.users.domain.model.User;
import es.aromano.users.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/activar-desactivar-usuario", method = RequestMethod.POST)
	public ResponseEntity<Void> desactivarUsuario(@RequestBody int idUsuario){
		
		User user = userService.findUserById(idUsuario);
		
		if(user == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		userService.toggleActivarUsuario(user);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
