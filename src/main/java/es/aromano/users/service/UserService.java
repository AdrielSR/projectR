package es.aromano.users.service;

import es.aromano.empresas.exceptions.EmpresaException;
import es.aromano.users.exceptions.UserException;
import es.aromano.users.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService{

    User findByEmail(String email);

    User findByUsername(String usermname);

    User createUser(User newUser) throws EmpresaException, UserException;

}
