package es.aromano.reservas;


import es.aromano.reservas.domain.model.Reserva;
import org.junit.Test;
import java.util.List;
import static es.aromano.utils.ReservaTestUtils.*;

public class ReservaRecurrenteTest extends ReservaSolapadaTest {


        @Test
        public void generar_reservas_recurrentes_diario(){

            Reserva reserva = crearReservaRecurrente(dailyRule());

            List<Reserva> reservasRecurrentes = reserva.calcularReservas();

            reservasRecurrentes
                .forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

        }

        @Test
        public void generar_reservas_recurrentes_semanal(){

            Reserva reserva = crearReservaRecurrente(weeklyRule());

            List<Reserva> reservasRecurrentes = reserva.calcularReservas();

            reservasRecurrentes
                    .forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

        }

    @Test
        public void generar_reservas_recurrentes_mensual(){

            Reserva reserva = crearReservaRecurrente(monthlyRule());

            List<Reserva> reservasRecurrentes = reserva.calcularReservas();

            reservasRecurrentes
                    .forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

        }

    @Test
    public void generar_reservas_recurrentes_anual(){

        Reserva reserva = crearReservaRecurrente(yearlyRule());

        List<Reserva> reservasRecurrentes = reserva.calcularReservas();

        reservasRecurrentes
                .forEach(r -> System.out.println(r.getInicio().toString("dd/MM/yyyy HH:mm") + " - " + r.getFin().toString("dd/MM/yyyy HH:mm")));

    }





}
