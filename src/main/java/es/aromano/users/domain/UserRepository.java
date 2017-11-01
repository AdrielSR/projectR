package es.aromano.users.domain;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.aromano.users.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int idUsuario);
	
	User findByEmail(String email);

    User findByEmailAndEnabledTrue(String email);

    User findByUsername(String usermname);

    User findByUsernameAndEnabledTrue(String usermname);

    @Query("from User u where u.empresa.id = ?#{principal.empresa.id}")
	List<User> findUsuariosEmpresaLogada();

    @Query("from User u where u.enabled = 1 and u.empresa.id = ?#{principal.empresa.id}")
    List<User> findUsuariosActivosEmpresaLogada();
    
    @Query("from User u where u.enabled = 0 and u.empresa.id = ?#{principal.empresa.id}")
    List<User> findUsuariosDesactivosEmpresaLogada();
    
    @Query("from User u where u.id = :idUsuario and u.empresa.id = ?#{principal.empresa.id}")
    User findUsuarioEmpresaLogada(@Param("idUsuario") int idUsuario);

    @Query("from User u where u.enabled = 1 and u.empresa.id = ?#{principal.empresa.id} and u.id <> ?#{principal.id} and (u.email like concat('%' , :term, '%') or u.username like concat('%' , :term, '%'))")
    List<User> findUsuariosActivosEnEmpresaByTerm(@Param("term")String term);
}
