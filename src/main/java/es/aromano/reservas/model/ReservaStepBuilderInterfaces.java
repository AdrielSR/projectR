package es.aromano.reservas.model;


import es.aromano.espacios.model.Espacio;
import es.aromano.users.model.User;
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
