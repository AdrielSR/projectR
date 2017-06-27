package es.aromano;


import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.Reserva;
import org.joda.time.DateTime;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class ReservaSolapadaTest extends ProjectApplicationTests{


    private static final DateTime start = new DateTime(2017, 11, 15, 10, 30);
    private static final DateTime end = new DateTime(2017, 11, 15, 11, 30);



    @Test
    public void reserva_solapada_caso_1(){

       DateTime start1 = new DateTime(2017, 11, 15, 10, 20);
       DateTime end1 = new DateTime(2017, 11, 15, 10, 50);

       Reserva reserva1 = crearReservaFrom(new RangoDateTime(start, end));
       Reserva reserva2 = crearReservaFrom(new RangoDateTime(start1, end1));

       assertThat(reserva1.solapa(reserva2)).isTrue();

    }

    @Test
    public void reserva_solapada_caso_2(){

        DateTime start1 = new DateTime(2017, 11, 15, 10, 40);
        DateTime end1 = new DateTime(2017, 11, 15, 11, 50);

        Reserva reserva1 = crearReservaFrom(new RangoDateTime(start, end));
        Reserva reserva2 = crearReservaFrom(new RangoDateTime(start1, end1));

        assertThat(reserva1.solapa(reserva2)).isTrue();

    }

    @Test
    public void reserva_solapada_caso_3(){

        DateTime start1 = new DateTime(2017, 11, 15, 10, 50);
        DateTime end1 = new DateTime(2017, 11, 15, 11, 10);

        Reserva reserva1 = crearReservaFrom(new RangoDateTime(start, end));
        Reserva reserva2 = crearReservaFrom(new RangoDateTime(start1, end1));

        assertThat(reserva1.solapa(reserva2)).isTrue();

    }

    @Test
    public void reserva_solapada_caso_4(){

        DateTime start1 = new DateTime(2017, 11, 15, 10, 10);
        DateTime end1 = new DateTime(2017, 11, 15, 11, 50);

        Reserva reserva1 = crearReservaFrom(new RangoDateTime(start, end));
        Reserva reserva2 = crearReservaFrom(new RangoDateTime(start1, end1));

        assertThat(reserva1.solapa(reserva2)).isTrue();

    }

    private Reserva crearReservaFrom(RangoDateTime rango) {

        Reserva reserva = new Reserva();
        reserva.setRango(rango);

        return reserva;
    }

}
