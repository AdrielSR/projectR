package es.aromano.reservas.web;

import es.aromano.reservas.domain.model.Respuesta;

public class InvitacionDTO {

    private long idReserva;
    private Respuesta respuesta;

    public InvitacionDTO() {
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }


    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
}
