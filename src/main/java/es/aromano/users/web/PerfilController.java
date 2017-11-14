package es.aromano.users.web;

import es.aromano.users.domain.exceptions.UserException;
import es.aromano.users.service.UserService;
import es.aromano.users.web.dto.ChangePasswordDTO;
import es.aromano.users.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PerfilController {

    @Autowired
    private UserService userService;


    @GetMapping("/perfil")
    public String verPerfil(Model model){
        model.addAttribute("view", "perfil");

        return "index";
    }

    @PostMapping("/editar-datos-usuario")
    public ResponseEntity<Void> editarDatosPerfil(@RequestBody UserDTO userDTO){
        userService.modificarDatosPerfil(userDTO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/editar-password-usuario")
    public ResponseEntity<Void> editarPasswordPerfil(@RequestBody ChangePasswordDTO changePasswordDTO) throws UserException {
        userService.modificarPasswordPerfil(changePasswordDTO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
