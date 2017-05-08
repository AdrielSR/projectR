package es.aromano.reservas.repository;

import java.util.List;

import org.joda.time.DateTime;
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

	@Query("from Reserva r where r.espacio.id = :idEspacio and " +
			"((r.rango.inicio between :inicio and :fin) or (r.rango.fin between :inicio and :fin) " +
            " or (:inicio between r.rango.inicio and r.rango.fin) or (:fin between r.rango.inicio and r.rango.fin))")
    List<Reserva> findReservasEspacioEnRango(@Param("inicio") DateTime inicio, @Param("fin") DateTime fin, @Param("idEspacio") int idEspacio);
}
