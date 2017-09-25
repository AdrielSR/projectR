package es.aromano.reservas.service;

import java.util.List;

import es.aromano.reservas.domain.excepciones.ReservaSolapadaException;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.web.ReservaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ReservaService {

	List<Reserva> reservasUsuario();

	Page<Reserva> reservasUsuario(PageRequest pageRequest);

    List<Reserva> findAllReservasDeUnEspacio(int idEspacio);
    
    List<Reserva> findReservasUsuarioDeUnEspacio(int idEspacio);
    
    List<Reserva> findReservasNoUsuarioDeUnEspacio(int idEspacio);

    List<Reserva> findReservasConflictivasEnEspacioYRango(int idEspacio, RangoDateTime rango);

    Reserva crearReserva(ReservaDTO reservaDTO) throws ReservaSolapadaException;

    boolean esPosibleReservarEspacioEnRango(RangoDateTime rango, int idEspacio);

    boolean esPosibleEditarReservaEnEspacio(RangoDateTime rango, int idEspacio, long idReserva);

    Reserva findReservaByIdReserva(long idReserva);

    boolean canAccessUser(long idReserva);

    Reserva editarReserva(ReservaDTO reservaDTO) throws ReservaSolapadaException;

    void eliminarReserva(long idReserva);
}
