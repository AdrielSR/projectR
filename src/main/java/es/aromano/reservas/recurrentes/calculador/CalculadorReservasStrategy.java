package es.aromano.reservas.recurrentes.calculador;


import es.aromano.reservas.domain.model.Reserva;

import java.util.List;

public interface CalculadorReservasStrategy {

    List<Reserva> calcular();

}
