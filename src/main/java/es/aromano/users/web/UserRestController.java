package es.aromano.users.web;

import es.aromano.users.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.aromano.users.domain.model.User;
import es.aromano.users.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

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



	@GetMapping("/buscar-usuarios-empresa/{term}")
	public List<UserDTO> buscarUsuarios(@PathVariable("term") String term){

		return userService.findUsuariosActivosEnEmpresaByTerm(term).stream()
									.map(user -> UserDTO.from(user))
									.collect(Collectors.toList());
	}


	
}
