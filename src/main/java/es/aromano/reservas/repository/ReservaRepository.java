package es.aromano.reservas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.aromano.reservas.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	@Query("from Reserva r where r.user.id = ?#{principal.id}")
	List<Reserva> reservasUsuario();

	@Query("select r from Reserva r join r.espacio es join es.edificio ed" +
			" where ed.empresa.id = ?#{principal.empresa.id}" +
			" and es.activo = 1 and ed.activo = 1 and es.id = :idEspacio")
    List<Reserva> findReservasByIdEspacio(@Param("idEspacio") int idEspacio);
}
