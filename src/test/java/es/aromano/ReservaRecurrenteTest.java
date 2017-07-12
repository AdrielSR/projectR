package es.aromano;


import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.recurrentes.domain.model.Frecuency;
import es.aromano.reservas.recurrentes.domain.model.RRule;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import es.aromano.users.domain.model.User;
import org.junit.Test;

import java.util.List;

public class ReservaRecurrenteTest extends ReservaSolapadaTest {


        @Test
        public void generar_reservas_recurrentes_mensual(){

            Reserva reserva = crearReservaFrom(new RangoDateTime(start, end));
            reserva.setEspacio(new Espacio());
            reserva.setUser(new User());

            ReglasRecurrencia reglas = crearReglasMensual();
            reserva.setReglas(reglas);

            List<Reserva> reservasRecurrentes = reserva.calcularReservas();

            reservasRecurrentes.stream().
                    forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

        }

    private ReglasRecurrencia crearReglasMensual() {
        RRule rrule = new RRule(Frecuency.MONTHLY, 1);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }


    @Test
    public void generar_reservas_recurrentes_anual(){

        Reserva reserva = crearReservaFrom(new RangoDateTime(start, end));
        reserva.setEspacio(new Espacio());
        reserva.setUser(new User());

        ReglasRecurrencia reglas = crearReglasAnual();
        reserva.setReglas(reglas);

        List<Reserva> reservasRecurrentes = reserva.calcularReservas();

        reservasRecurrentes.stream().
                forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

    }

    private ReglasRecurrencia crearReglasAnual() {
        RRule rrule = new RRule(Frecuency.YEARLY, 1);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }



}
