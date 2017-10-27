package es.aromano.reservas.web;

import es.aromano.reservas.domain.InvitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvitacionesController {

    @Autowired
    private InvitacionRepository invitacionRepository;

    @GetMapping("/invitaciones")
    public String listarInvitaciones(Model model){

        model.addAttribute("invitaciones", invitacionRepository.findInvitacionesUsuario());
        model.addAttribute("view", "listar-invitaciones");

        return "index";
    }



}
