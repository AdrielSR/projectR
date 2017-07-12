package es.aromano.reservas.recurrentes.calculador;


import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.recurrentes.domain.model.Frecuency;

import static es.aromano.reservas.recurrentes.domain.model.Frecuency.*;

public class CalculadorReservasFactory {


    public static CalculadorReservasStrategy getCalculador(Reserva reserva){

        Frecuency frecuency = reserva.getReglas().getRrule().getFrecuency();

        if(frecuency == DAILY){
            return new CalculadorReservasStrategyDiario();
        }
        else if(frecuency == WEEKlY){
            return new CalculadorReservasStrategySemanal();
        }
        else if(frecuency == MONTHLY){
            return new CalculadorReservasStrategyMensual(reserva);
        }
        else {
            return new CalculadorReservasStrategyAnual(reserva);
        }

    }


}
