package es.aromano.reservas.service;

import java.util.List;
import java.util.Objects;

import es.aromano.espacios.service.EspacioService;
import es.aromano.reservas.excepciones.ReservaSolapadaException;
import es.aromano.reservas.model.RangoDateTime;
import es.aromano.reservas.model.ReservaStepBuilder;
import es.aromano.reservas.web.ReservaDTO;
import es.aromano.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.reservas.model.Reserva;
import es.aromano.reservas.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private EspacioService espacioService;
	
	@Override
	public List<Reserva> reservasUsuario() {
		return reservaRepository.findReservasUsuario();
	}

	@Override
	public List<Reserva> findReservasByIdEspacio(int idEspacio) {
		return reservaRepository.findReservasByIdEspacio(idEspacio);
	}

	@Override
	public Reserva crearReserva(ReservaDTO reservaDTO) throws ReservaSolapadaException {

		if(!esPosibleReservarEnEspacio(new RangoDateTime(reservaDTO.getStart(), reservaDTO.getEnd()), reservaDTO.getIdEspacio())){
			throw new ReservaSolapadaException(String.format("No se ha podido crear la reserva debido a que esta solapa con otra"));
		}

		Reserva newReserva = ReservaStepBuilder.builder()
								.propietario(userService.getCurrentUser())
								.lugar(espacioService.findEspacio(reservaDTO.getIdEspacio()))
								.desde(reservaDTO.getStart())
								.hasta(reservaDTO.getEnd())
								.asunto(reservaDTO.getTitle())
								.build();


		return reservaRepository.save(newReserva);
	}

	@Override
	public boolean esPosibleReservarEnEspacio(RangoDateTime rango, int idEspacio) {
		return reservaRepository.findReservasEspacioEnRango(rango.getInicio(), rango.getFin(), idEspacio).isEmpty();
	}

	@Override
	public boolean esPosibleEditarReservaEnEspacio(RangoDateTime rango, int idEspacio, long idReserva) {
		return reservaRepository.findReservasEspacioEnRango(rango.getInicio(), rango.getFin(), idEspacio, idReserva).isEmpty();
	}

	@Override
	public Reserva findReservaByIdReserva(long idReserva) {
		return reservaRepository.findOne(idReserva);
	}

	@Override
	public boolean canAccessUser(long idReserva) {
		return Objects.nonNull(reservaRepository.findReservaUsuario(idReserva));
	}

	@Override
	public Reserva editarReserva(ReservaDTO reservaDTO) throws ReservaSolapadaException {

		if(!esPosibleEditarReservaEnEspacio(new RangoDateTime(reservaDTO.getStart(), reservaDTO.getEnd()), reservaDTO.getIdEspacio(), reservaDTO.getId())){
			throw new ReservaSolapadaException(String.format("No se ha podido crear la reserva debido a que esta solapa con otra"));
		}

		Reserva reserva = findReservaByIdReserva(reservaDTO.getId());
		reserva.setAsunto(reservaDTO.getTitle());
		reserva.setRango(new RangoDateTime(reservaDTO.getStart(), reservaDTO.getEnd()));

		return reservaRepository.save(reserva);
	}

}
