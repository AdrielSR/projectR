package es.aromano.reservas.web;

import es.aromano.reservas.domain.model.Respuesta;
import es.aromano.reservas.service.InvitacionService;
import es.aromano.reservas.web.dto.InvitacionDTO;
import es.aromano.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class InvitacionesController {

    @Autowired
    private InvitacionService invitacionService;

    @Autowired
    private UserService userService;

    @GetMapping("/invitaciones")
    public String listarInvitaciones(Model model){

        model.addAttribute("invitaciones", invitacionService.findInvitacionesUsuario());
        model.addAttribute("respuestas", Respuesta.values());
        model.addAttribute("view", "listar-invitaciones");

        return "index";
    }

    @PostMapping("/responder-invitacion")
    public ResponseEntity<Void> responderInvitacion(@RequestBody InvitacionDTO invitacionDTO){

        int idUsuarioLogado = userService.getCurrentUser().getId();
        invitacionService.responder(invitacionDTO.getIdReserva(), idUsuarioLogado, invitacionDTO.getRespuesta());

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
