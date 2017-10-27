package es.aromano.reservas.domain.model;

import java.io.Serializable;

public class InvitacionId implements Serializable {

    private int user;
    private int reserva;

    protected InvitacionId() {
    }

    public InvitacionId(int user, int reserva) {
        this.user = user;
        this.reserva = reserva;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvitacionId that = (InvitacionId) o;

        if (user != that.user) return false;
        return reserva == that.reserva;
    }

    @Override
    public int hashCode() {
        int result = user;
        result = 31 * result + reserva;
        return result;
    }
}
