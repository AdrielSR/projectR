package es.aromano.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.aromano.reservas.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	
	
}
