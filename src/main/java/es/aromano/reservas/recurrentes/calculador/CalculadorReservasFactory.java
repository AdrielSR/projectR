package es.aromano.reservas.recurrentes.calculador;


import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.recurrentes.domain.model.Frecuency;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;

import static es.aromano.reservas.recurrentes.domain.model.Frecuency.*;

public class CalculadorReservasFactory {


    public static CalculadorReservasStrategy getCalculador(Reserva reserva){

        Frecuency frecuency = reserva.getReglas().getRrule().getFrecuency();

        if(frecuency == DAILY){
            return new CalculadorReservasDiarioStrategy();
        }
        else if(frecuency == WEEKlY){
            return new CalculadorReservasSemanalStrategy();
        }
        else if(frecuency == MONTHLY){
            return new CalculadorReservasMensualStrategy(reserva);
        }
        else {
            return new CalculadorReservasAnualStrategy();
        }

    }


}
