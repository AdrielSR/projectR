package es.aromano.edificios.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.aromano.edificios.model.Edificio;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Integer> {

	@Query("from Edificio e where e.empresa.id = ?#{principal.empresa.id}")
	List<Edificio> edificiosEmpresaLogada();
	
}
