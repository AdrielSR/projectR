package es.aromano.reservas.domain.model;


import es.aromano.espacios.domain.model.Espacio;
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
        AsuntoStep hasta(DateTime fin);
    }

    interface AsuntoStep extends BuildStep{
        BuildStep asunto(String asunto);
    }

    interface BuildStep{
        Reserva build();
    }

}
