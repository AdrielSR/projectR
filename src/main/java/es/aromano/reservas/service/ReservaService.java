package es.aromano.reservas.service;

import java.util.List;

import es.aromano.reservas.domain.excepciones.ReservaSolapadaException;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.web.ReservaDTO;

public interface ReservaService {

	List<Reserva> reservasUsuario();

    List<Reserva> findReservasByIdEspacio(int idEspacio);

    Reserva crearReserva(ReservaDTO reservaDTO) throws ReservaSolapadaException;

    boolean esPosibleReservarEnEspacio(RangoDateTime rango, int idEspacio);

    boolean esPosibleEditarReservaEnEspacio(RangoDateTime rango, int idEspacio, long idReserva);

    Reserva findReservaByIdReserva(long idReserva);

    boolean canAccessUser(long idReserva);

    Reserva editarReserva(ReservaDTO reservaDTO) throws ReservaSolapadaException;

    void eliminarReserva(long idReserva);
}
