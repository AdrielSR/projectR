package es.aromano.reservas.domain.model;

import java.io.Serializable;

public class InvitacionId implements Serializable {

    private int user;
    private long reserva;

    protected InvitacionId() {
    }

    public InvitacionId(int user, long reserva) {
        this.user = user;
        this.reserva = reserva;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public long getReserva() {
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
        result = 31 * result + (int) (reserva ^ (reserva >>> 32));
        return result;
    }
}
