package es.aromano.users.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.aromano.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByUsername(String usermname);

    @Query("from User u where u.empresa.id = ?#{principal.empresa.id}")
	List<User> findUsuariosEmpresaLogada();
    
    @Query("from User u where u.id = :idUsuario and u.empresa.id = ?#{principal.empresa.id}")
    User findUsuarioEmpresaLogada(@Param("idUsuario") int idUsuario);

}
