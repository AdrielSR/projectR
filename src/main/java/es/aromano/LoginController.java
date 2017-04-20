package es.aromano;

import es.aromano.users.model.User;
import es.aromano.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String resigtration(){
        return "registration";
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String doResigtration(@ModelAttribute User user){

        User newUser = userService.createUser(user);

        if(newUser == null){
            return "redirect:/registration?error";
        }

        return "redirect:/login";
    }

}
