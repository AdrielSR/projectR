package es.aromano.users.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import es.aromano.empresas.domain.exceptions.EmpresaException;
import es.aromano.users.domain.exceptions.UserException;
import es.aromano.users.domain.model.Role;
import es.aromano.users.domain.model.User;


public interface UserService extends UserDetailsService{

    User findUsuarioEmpresaLogada(int idUsuario);

    User findUserById(int idUsuario);

	User findByEmail(String email);

    User findByUsername(String usermname);

    User createEmpresaAndUser(User user) throws EmpresaException, UserException;

	List<User> findUsuariosEmpresaLogada();

	User createUser(User user);

	User editUser(int idUsuario, User editedUser) throws UserException;
	
	List<Role> findAllRoles();
	
	List<User> findUsuariosActivosEmpresaLogada();
	
	List<User> findUsuariosDesactivosEmpresaLogada();
	
	User toggleActivarUsuario(User user);
	
	User getCurrentUser();

}
