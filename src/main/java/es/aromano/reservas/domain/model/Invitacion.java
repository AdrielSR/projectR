package es.aromano.reservas.domain.model;

import es.aromano.users.domain.model.User;

import javax.persistence.*;

import static es.aromano.reservas.domain.model.Respuesta.*;

@Entity
@IdClass(InvitacionId.class)
public class Invitacion {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Respuesta respuesta;

    protected Invitacion() {
    }

    public Invitacion(User user, Reserva reserva) {
        this.user = user;
        this.reserva = reserva;
        this.respuesta = QUIZAS_ASISTA;
    }

    public User getUser() {
        return user;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invitacion that = (Invitacion) o;

        if (!user.equals(that.user)) return false;
        return reserva.equals(that.reserva);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + reserva.hashCode();
        return result;
    }
}
