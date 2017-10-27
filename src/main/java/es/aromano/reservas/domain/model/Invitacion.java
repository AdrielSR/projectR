package es.aromano.reservas.domain.model;

import es.aromano.users.domain.model.User;

import javax.persistence.*;

import static es.aromano.reservas.domain.model.Respuesta.*;

@Entity
@IdClass(InvitacionId.class)
public class Invitacion {

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "RESERVA_ID")
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


    public void asistire(){
        this.respuesta = ASISTIRE;
    }

    public void noAsistire(){
        this.respuesta = NO_ASISTIRE;
    }

}
