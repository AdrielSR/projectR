package es.aromano;

import es.aromano.users.domain.exceptions.UserException;
import es.aromano.users.domain.model.User;
import es.aromano.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistroController {

    @Autowired
    private UserService userService;


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String resigtration(){
        return "registration";
    }


    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String doResigtration(@ModelAttribute User user, Model model){

        try {

            userService.createEmpresaAndUser(user);

        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }

        return "redirect:/login";
    }


}
