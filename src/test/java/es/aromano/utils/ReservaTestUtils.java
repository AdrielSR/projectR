package es.aromano.utils;

import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.domain.model.ReservaStepBuilder;
import es.aromano.reservas.recurrentes.domain.model.ExDate;
import es.aromano.reservas.recurrentes.domain.model.RRule;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import es.aromano.users.domain.model.User;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static es.aromano.reservas.recurrentes.domain.model.Frecuency.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservaTestUtils {

    private static final DateTime start = new DateTime(2017, 11, 15, 10, 30);
    private static final DateTime end = new DateTime(2017, 11, 15, 11, 30);


    public static Reserva crearMockReserva(DateTime inicio, DateTime fin){
        Reserva reserva = mock(Reserva.class);
        when(reserva.getInicio()).thenReturn(inicio);
        when(reserva.getFin()).thenReturn(fin);
        when(reserva.getEspacio()).thenReturn(new Espacio());
        when(reserva.getUser()).thenReturn(new User());


        return reserva;
    }

    public static Reserva crearDefaultMockReserva(){
        return crearMockReserva(start, end);
    }

    public static Reserva crearReserva(DateTime inicio, DateTime fin){
        return ReservaStepBuilder.builder()
                .propietario(new User())
                .lugar(new Espacio())
                .desde(inicio)
                .hasta(fin)
                .build();
    }

    public static Reserva crearDefaultReserva(){
        return crearReserva(start, end);
    }

    public static Reserva crearReservaRecurrente(ReglasRecurrencia reglas){
        Reserva reserva = crearDefaultReserva();
        reserva.setReglas(reglas);

        return reserva;
    }

    public static ReglasRecurrencia dailyRule() {
        RRule rrule = new RRule(DAILY, 3);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }

    public static ReglasRecurrencia weeklyRule() {
        RRule rrule = new RRule(WEEKLY, 1);
        rrule.setCount(10);
        String daysOfWeek = "1,3,5";
        rrule.setDaysOfWeek(daysOfWeek);

        ReglasRecurrencia reglas = new ReglasRecurrencia(rrule);

        List<ExDate> exDates = new ArrayList<>();
        RangoDateTime rangoExDate = new RangoDateTime(start, end);
        exDates.add(new ExDate(rangoExDate));

        reglas.setExdate(exDates);

        return reglas;
    }


    public static ReglasRecurrencia monthlyRule() {
        RRule rrule = new RRule(MONTHLY, 1);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }

    public static ReglasRecurrencia yearlyRule() {
        RRule rrule = new RRule(YEARLY, 1);
        rrule.setCount(4);

        return new ReglasRecurrencia(rrule);
    }


}
