package es.aromano.users.service;


import java.util.List;
import java.util.Objects;

import es.aromano.users.domain.ImagenRepository;
import es.aromano.users.domain.model.Imagen;
import es.aromano.users.web.dto.ChangePasswordDTO;
import es.aromano.users.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.aromano.empresas.domain.model.Empresa;
import es.aromano.empresas.service.EmpresaService;
import es.aromano.users.domain.exceptions.UserException;
import es.aromano.users.domain.model.User;
import es.aromano.users.domain.model.Role;
import es.aromano.users.domain.RoleRepository;
import es.aromano.users.domain.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRespository;
    
    @Autowired
    private RoleRepository roleRepository; 

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ImagenRepository imagenRepository;


    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @Override
    public void checkIfUserExist(User user) throws UserException {
        User u = findByUsername(user.getUsername());

        if(Objects.isNull(u)){
            u = findByEmail(user.getEmail());
        }

        if(Objects.nonNull(u)){
            throw new UserException("Lo sentimos. Ya existe un usuario con ese username");
        }
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
    public User createEmpresaAndUser(User user) throws UserException {

    	Empresa newEmpresa = empresaService.createEmpresa(user.getEmpresa());

        checkIfUserExist(user);

        User newUser = new User(user.getUsername(), user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.addRole(roleRepository.findByRole("ROLE_ADMIN"));
        newUser.setEmpresa(newEmpresa);
        newUser = userRespository.save(newUser);
        
        newEmpresa.addUser(newUser);

        return newUser;
    }

	@Override
	public List<User> findUsuariosEmpresaLogada() {
		return userRespository.findUsuariosEmpresaLogada();
	}

	@Override
	public User createUser(User user) throws UserException {
        checkIfUserExist(user);

		User newUser = new User(user.getUsername(), user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
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


    @Override
    public List<User> findUsuariosActivosEnEmpresaByTerm(String term) {
        return userRespository.findUsuariosActivosEnEmpresaByTerm(term);
    }

    @Override
    public void modificarDatosPerfil(UserDTO userDTO) {
        User currentUser = getCurrentUser();

        currentUser.setUsername(userDTO.getUsername());
        currentUser.setEmail(userDTO.getEmail());
        currentUser.setAvatar(imagenRepository.findOne(userDTO.getIdAvatar()));

        updateUserInSession(currentUser);


    }

    @Override
    public void modificarPasswordPerfil(ChangePasswordDTO changePasswordDTO) throws UserException {
        User currentUser = getCurrentUser();

        if(!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), currentUser.getPassword())){
            throw new UserException("La contrase�a introducida no coincide con la actual.");
        }

        currentUser.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));

        updateUserInSession(currentUser);
    }

    @Override
    public List<Imagen> findAllAvatares() {
        return imagenRepository.findAll();
    }

    private void updateUserInSession(User user) {
        // Actualiza el usuario actual sin cerrar sesi�n
        Authentication request = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(request);

        // Guarda los cambios en la base de datos
        userRespository.save(user);
    }

}
