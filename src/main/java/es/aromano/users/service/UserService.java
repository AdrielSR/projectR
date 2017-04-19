package es.aromano.users.service;

import es.aromano.users.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService{

    User findByEmail(String email);

    User findByUsername(String usermname);

    User createUser(User newUser);

}
