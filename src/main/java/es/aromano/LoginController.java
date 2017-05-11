package es.aromano;

import es.aromano.empresas.domain.exceptions.EmpresaException;
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
public class LoginController {

    @Autowired
    private UserService userService;


    ////// LOGIN //////

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/login-error", method = RequestMethod.GET)
    public String loginError(Model model){
        model.addAttribute("error", "Username o password incorrectos");

        return "login";
    }


    ////// REGISTRO //////

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String resigtration(){
        return "registration";
    }

    @RequestMapping(value="/registration-error", method = RequestMethod.GET)
    public String resigtrationError(Model model){
        model.addAttribute("error", "No se ha podido registrar");

        return "registration";
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String doResigtration(@ModelAttribute User user) throws EmpresaException, UserException{

        User newUser = userService.createEmpresaAndUser(user);

        if(newUser == null){
            return "redirect:/registration-error";
        }

        return "redirect:/login";
    }

}
