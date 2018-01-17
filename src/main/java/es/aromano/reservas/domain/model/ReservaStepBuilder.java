package es.aromano.reservas.domain.model;


import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.domain.model.ReservaStepBuilderInterfaces.*;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import es.aromano.users.domain.model.User;
import org.joda.time.DateTime;


public class ReservaStepBuilder implements UserStep, LugarStep, DesdeStep, HastaStep, ReglasStep, AsuntoStep, BuildStep {

    private User user;
    private Espacio espacio;
    private DateTime inicio;
    private DateTime fin;
    private String asunto = "";
    private ReglasRecurrencia reglas;

    public static UserStep builder(){
        return new ReservaStepBuilder();
    }

    @Override
    public LugarStep propietario(User user) {
        this.user = user;
        return this;
    }

    @Override
    public DesdeStep lugar(Espacio espacio) {
        this.espacio = espacio;
        return this;
    }

    @Override
    public HastaStep desde(DateTime inicio) {
        this.inicio = inicio;
        return this;
    }

    @Override
    public ReglasStep hasta(DateTime fin) {
        this.fin = fin;
        return this;
    }

    @Override
    public AsuntoStep reglas(ReglasRecurrencia reglas) {
        this.reglas = reglas;
        return this;
    }

    @Override
    public BuildStep asunto(String asunto) {
        this.asunto = asunto;
        return this;
    }

    @Override
    public Reserva build() {
        String asunto = this.asunto;
        RangoDateTime rango = new RangoDateTime(this.inicio, this.fin);
        Espacio espacio = this.espacio;
        User user = this.user;
        ReglasRecurrencia reglas = this.reglas;

        Reserva reserva = new Reserva(asunto, rango, espacio, user);
        reserva.setReglas(reglas);

        return reserva;
    }
}
