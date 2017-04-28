package es.aromano.users.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.aromano.empresas.exceptions.EmpresaException;
import es.aromano.empresas.model.Empresa;
import es.aromano.empresas.service.EmpresaService;
import es.aromano.users.exceptions.UserException;
import es.aromano.users.model.User;
import es.aromano.users.model.Role;
import es.aromano.users.repository.RoleRepository;
import es.aromano.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRespository;
    
    @Autowired
    private RoleRepository roleRepository; 

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private EmpresaService empresaService;



    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRespository.findByEmailAndEnabledTrue(username);
        if (user == null)  {
            user = userRespository.findByUsernameAndEnabledTrue(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }

        return user;
    }

    @Override
	public User findUsuarioEmpresaLogada(int idUsuario) {
		return userRespository.findUsuarioEmpresaLogada(idUsuario);
	}

    @Override
    public User findUserById(int idUsuario) {
        return userRespository.findOne(idUsuario);
    }

    @Override
    public User findByEmail(String email) {
        return userRespository.findByEmail(email);
    }

    @Override
    public User findByUsername(String userName) {
        return userRespository.findByUsername(userName);
    }

    @Override
    public User createEmpresaAndUser(User user) throws EmpresaException, UserException {

    	Empresa newEmpresa = empresaService.createEmpresa(user.getEmpresa());
    	
    	if(newEmpresa == null){
    		throw new EmpresaException("Error creando empresa");
    	}
    	
        User newUser = new User(user.getUsername(), user.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.addRole(roleRepository.findByRole("ROLE_ADMIN"));
        newUser.setEmpresa(newEmpresa);
        newUser = userRespository.save(newUser);
        
        if(newUser == null){
        	throw new UserException("Error creando usuario");
        }
        
        newEmpresa.addUser(newUser);

        return newUser;
    }

	@Override
	public List<User> findUsuariosEmpresaLogada() {
		return userRespository.findUsuariosEmpresaLogada();
	}

	@Override
	public User createUser(User user) {
		User newUser = new User(user.getUsername(), user.getEmail());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		newUser.addRole(roleRepository.findByRole("ROLE_USER"));
		newUser.setEmpresa(getCurrentUser().getEmpresa());
		newUser = userRespository.save(newUser);
		
		return newUser;
	}

    @Override
    public User editUser(int idUsuario, User editedUser) throws UserException {

    	User currentUser = findUserById(idUsuario);

		if(currentUser == null){
			throw new UserException(String.format("No se ha encontrado el user con [id]= %d", idUsuario));
		}
		
    	currentUser.setUsername(editedUser.getUsername());
    	currentUser.setEmail(editedUser.getEmail());
    	currentUser.setRoles(editedUser.getRoles());

        return userRespository.save(currentUser);
    }

    @Override
    public List<Role> findAllRoles(){
    	return roleRepository.findAll();
    }

	@Override
	public List<User> findUsuariosActivosEmpresaLogada() {
		return userRespository.findUsuariosActivosEmpresaLogada();
	}

	@Override
	public List<User> findUsuariosDesactivosEmpresaLogada() {
		return userRespository.findUsuariosDesactivosEmpresaLogada();
	}

	@Override
	public User toggleActivarUsuario(User user) {
        if(user.isEnabled()){
            user.desactivar();
        }
        else{
            user.activar();
        }

		return userRespository.save(user);
	}
    
    
}
