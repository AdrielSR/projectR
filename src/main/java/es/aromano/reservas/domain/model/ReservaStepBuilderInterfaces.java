package es.aromano.reservas.domain.model;


import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import es.aromano.users.domain.model.User;
import org.joda.time.DateTime;


public interface ReservaStepBuilderInterfaces {

    interface UserStep{
        LugarStep propietario(User user);
    }

    interface LugarStep{
        DesdeStep lugar(Espacio espacio);
    }

    interface DesdeStep{
        HastaStep desde(DateTime inicio);
    }

    interface HastaStep{
        ReglasStep hasta(DateTime fin);
    }

    interface ReglasStep extends AsuntoStep{
        AsuntoStep reglas(ReglasRecurrencia reglas);
    }

    interface AsuntoStep extends BuildStep{
        BuildStep asunto(String asunto);
    }

    interface BuildStep{
        Reserva build();
    }

}
