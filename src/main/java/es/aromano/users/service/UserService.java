package es.aromano.users.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import es.aromano.empresas.exceptions.EmpresaException;
import es.aromano.users.exceptions.UserException;
import es.aromano.users.model.User;


public interface UserService extends UserDetailsService{

    User findByEmail(String email);

    User findByUsername(String usermname);

    User createUser(User user) throws EmpresaException, UserException;

	List<User> findUsuariosEmpresa();

	User createUserEmpresa(User user);
}
