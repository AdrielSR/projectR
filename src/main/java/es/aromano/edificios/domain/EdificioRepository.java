package es.aromano.edificios.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import es.aromano.edificios.domain.model.Edificio;


@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Integer> {

	@Query("from Edificio e where e.activo = 1 and e.empresa.id = ?#{principal.empresa.id}")
	List<Edificio> edificiosActivos();
	
	@Query("from Edificio e where e.activo = 0 and e.empresa.id = ?#{principal.empresa.id}")
	List<Edificio> edificiosDesactivos();
	
	@Query("from Edificio e where e.activo = 1 and e.id = :idEdificio and e.empresa.id = ?#{principal.empresa.id}")
	Edificio findEdificioActivo(@Param("idEdificio")int idEdificio);

}
