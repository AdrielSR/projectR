package es.aromano.users.service;


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
import es.aromano.users.model.UserRole;
import es.aromano.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRespository;

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
        UserDetails user = userRespository.findByEmail(username);
        if (user == null)  {
            user = userRespository.findByUsername(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }

        return user;
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
    public User createUser(User user) throws EmpresaException, UserException {

    	Empresa newEmpresa = empresaService.createEmpresa(user.getEmpresa());
    	
    	if(newEmpresa == null){
    		throw new EmpresaException("Error creando empresa");
    	}
    	
        User newUser = new User(user.getUsername(), user.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.addRole(new UserRole("ROLE_ADMIN"));
        newUser.setEmpresa(newEmpresa);
        newUser = userRespository.save(newUser);
        
        if(newUser == null){
        	throw new UserException("Error creando usuario");
        }
        
        newEmpresa.addUser(newUser);

        return newUser;
    }
}
