package es.aromano;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/login-error", method = RequestMethod.GET)
    public String loginError(Model model){
        model.addAttribute("error", "Username o password incorrectos");

        return "login";
    }


}
