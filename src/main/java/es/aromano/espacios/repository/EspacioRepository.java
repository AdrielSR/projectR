package es.aromano.espacios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.aromano.espacios.model.Espacio;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Integer> {

	@Query("select es from Espacio es join es.edificio ed "
			+ "where ed.empresa.id = ?#{principal.empresa.id} "
			+ "and ed.activo = 1 and es.activo = 1")
	List<Espacio> findEspaciosActivos();

	@Query("select es from Espacio es join es.edificio ed "
			+ "where ed.empresa.id = ?#{principal.empresa.id} "
			+ "and ed.activo = 1 and es.activo = 0")
	List<Espacio> findEspaciosDesactivos();
	
}
