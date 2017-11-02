package es.aromano.reservas.service;

import es.aromano.reservas.domain.model.Invitacion;
import es.aromano.reservas.domain.model.Respuesta;

import java.util.List;

public interface InvitacionService {

    List<Invitacion> findInvitacionesUsuario();

    void responder(long idReserva, int idUsuario, Respuesta respuesta);


}
