package es.aromano;


import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.recurrentes.domain.model.ExDate;
import es.aromano.reservas.recurrentes.domain.model.Frecuency;
import es.aromano.reservas.recurrentes.domain.model.RRule;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import es.aromano.users.domain.model.User;
import org.junit.Test;

import java.util.List;

public class ReservaRecurrenteTest extends ReservaSolapadaTest {


        @Test
        public void generar_reservas_recurrentes_diario(){

            Reserva reserva = crearReservaFrom(new RangoDateTime(start, end));
            reserva.setEspacio(new Espacio());
            reserva.setUser(new User());

            ReglasRecurrencia reglas = crearReglasDiario();
            reserva.setReglas(reglas);

            List<Reserva> reservasRecurrentes = reserva.calcularReservas();

            reservasRecurrentes.stream().
                    forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

        }

        @Test
        public void generar_reservas_recurrentes_semanal(){

            Reserva reserva = crearReservaFrom(new RangoDateTime(start, end));
            reserva.setEspacio(new Espacio());
            reserva.setUser(new User());

            ReglasRecurrencia reglas = crearReglasSemanal();
            reserva.setReglas(reglas);

            List<Reserva> reservasRecurrentes = reserva.calcularReservas();

            reservasRecurrentes.stream().
                    forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

        }

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

    private ReglasRecurrencia crearReglasDiario() {
        RRule rrule = new RRule(Frecuency.DAILY, 3);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }

    private ReglasRecurrencia crearReglasSemanal() {
        RRule rrule = new RRule(Frecuency.WEEKLY, 1);
        rrule.setCount(10);
        String daysOfWeek = "1,3,5";
        rrule.setDaysOfWeek(daysOfWeek);

        ReglasRecurrencia reglas = new ReglasRecurrencia(rrule);
        reglas.setExdate(new ExDate("17/11/2017,22/11/2017"));

        return reglas;
    }


    private ReglasRecurrencia crearReglasMensual() {
        RRule rrule = new RRule(Frecuency.MONTHLY, 1);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }

    private ReglasRecurrencia crearReglasAnual() {
        RRule rrule = new RRule(Frecuency.YEARLY, 1);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }



}
