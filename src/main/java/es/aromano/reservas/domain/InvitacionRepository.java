package es.aromano.reservas.domain;

import es.aromano.reservas.domain.model.Invitacion;
import es.aromano.reservas.domain.model.InvitacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvitacionRepository extends JpaRepository<Invitacion, InvitacionId> {

    @Query("from Invitacion i where i.reserva.id = :idReserva and i.user.id = :idUsuario")
    Invitacion findInvitacion(@Param("idReserva") long idReserva, @Param("idUsuario") int idUsuario);

    @Query("from Invitacion i where i.user.id = ?#{principal.id}")
    List<Invitacion> findInvitacionesUsuario();


}
