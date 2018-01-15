package es.aromano;


import es.aromano.reservas.domain.model.Reserva;
import org.joda.time.DateTime;

import static es.aromano.utils.ReservaTestUtils.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ReservaSolapadaTest extends ProjectApplicationTests{


    /**
     *        start-end
     *    |---------------|
     * |-------------|
     *   start1-end1
     */
    @Test
    public void reserva_solapada_caso_1(){

       DateTime start1 = new DateTime(2017, 11, 15, 10, 20);
       DateTime end1 = new DateTime(2017, 11, 15, 10, 50);

       Reserva reserva1 = crearDefaultMockReserva();
       Reserva reserva2 = crearMockReserva(start1, end1);

       when(reserva1.solapa(reserva2)).thenCallRealMethod();

       assertThat(reserva1.solapa(reserva2)).isTrue();

    }

    /**
     *        start-end
     *    |---------------|
     *            |-------------|
     *              start1-end1
     */
    @Test
    public void reserva_solapada_caso_2(){

        DateTime start1 = new DateTime(2017, 11, 15, 10, 40);
        DateTime end1 = new DateTime(2017, 11, 15, 11, 50);

        Reserva reserva1 = crearDefaultMockReserva();
        Reserva reserva2 = crearMockReserva(start1, end1);

        when(reserva1.solapa(reserva2)).thenCallRealMethod();

        assertThat(reserva1.solapa(reserva2)).isTrue();

    }

    /**
     *        start-end
     *    |---------------|
     *       |--------|
     *       start1-end1
     */
    @Test
    public void reserva_solapada_caso_3(){

        DateTime start1 = new DateTime(2017, 11, 15, 10, 50);
        DateTime end1 = new DateTime(2017, 11, 15, 11, 10);

        Reserva reserva1 = crearDefaultMockReserva();
        Reserva reserva2 = crearMockReserva(start1, end1);

        when(reserva1.solapa(reserva2)).thenCallRealMethod();

        assertThat(reserva1.solapa(reserva2)).isTrue();

    }

    /**
     *        start-end
     *    |---------------|
     * |---------------------|
     *       start1-end1
     */
    @Test
    public void reserva_solapada_caso_4(){

        DateTime start1 = new DateTime(2017, 11, 15, 10, 10);
        DateTime end1 = new DateTime(2017, 11, 15, 11, 50);

        Reserva reserva1 = crearDefaultMockReserva();
        Reserva reserva2 = crearMockReserva(start1, end1);

        when(reserva1.solapa(reserva2)).thenCallRealMethod();

        assertThat(reserva1.solapa(reserva2)).isTrue();

    }


}
