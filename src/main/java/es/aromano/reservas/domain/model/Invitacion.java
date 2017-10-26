package es.aromano.reservas.domain.model;

import es.aromano.users.domain.model.User;

import javax.persistence.Id;

public class Invitacion {

    private User user;

    private Reserva reserva;

    private RespuestaInvitacion respuesta;

    protected Invitacion() {
    }


}
