package es.aromano;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String home(){
        return "hello";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


}
