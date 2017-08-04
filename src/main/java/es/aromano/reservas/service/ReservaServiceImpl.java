package es.aromano.reservas.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import es.aromano.espacios.service.EspacioService;
import es.aromano.reservas.domain.excepciones.ReservaSolapadaException;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.ReservaStepBuilder;
import es.aromano.reservas.recurrentes.domain.model.RRule;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import es.aromano.reservas.web.ReservaDTO;
import es.aromano.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.domain.ReservaRepository;

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
	public List<Reserva> findAllReservasDeUnEspacio(int idEspacio) {
		return reservaRepository.findByIdEspacio(idEspacio);
	}
	
	@Override
	public List<Reserva> findReservasUsuarioDeUnEspacio(int idEspacio) {
		return reservaRepository.findReservasUsuarioDeUnEspacio(idEspacio);
	}
	
	@Override
	public List<Reserva> findReservasNoUsuarioDeUnEspacio(int idEspacio) {
		return reservaRepository.findReservasNoUsuarioDeUnEspacio(idEspacio);
	}

	@Override
	public List<Reserva> findReservasConflictivasEnEspacioYRango(int idEspacio, RangoDateTime rango) {
		return reservaRepository.findReservasConflictivasEnEspacioYRango(idEspacio, rango.getInicio(), rango.getFin());
	}

	@Override
	public Reserva crearReserva(ReservaDTO reservaDTO) throws ReservaSolapadaException {

		if(reservaDTO.isRecurrente()){
			return crearReservaRecurrente(reservaDTO);
		}

		return crearReservaSimple(reservaDTO);
	}

	private Reserva crearReservaRecurrente(ReservaDTO reservaDTO) throws ReservaSolapadaException {


		Reserva newReserva = ReservaStepBuilder.builder()
				.propietario(userService.getCurrentUser())
				.lugar(espacioService.findEspacio(reservaDTO.getIdEspacio()))
				.desde(reservaDTO.getStart())
				.hasta(reservaDTO.getEnd())
				.asunto(reservaDTO.getTitle())
				.build();

		newReserva.setReglas(reservaDTO.getReglas());

		newReserva.calcularReservas();
		List<Reserva> reservasConflictivas = findReservasConflictivasEnEspacioYRango(reservaDTO.getIdEspacio(), newReserva.getRangoRecurrencia());


		Optional<Reserva> reservaSolapada = reservasConflictivas.stream()
																.filter(r -> r.solapa(newReserva))
																.findFirst();

		if(reservaSolapada.isPresent()){
			throw new ReservaSolapadaException("No se ha podido crear la reserva debido a que esta solapa con otra.");
		}


		return reservaRepository.save(newReserva);
	}

	private Reserva crearReservaSimple(ReservaDTO reservaDTO) throws ReservaSolapadaException {

		if(!esPosibleReservarEspacioEnRango(new RangoDateTime(reservaDTO.getStart(), reservaDTO.getEnd()), reservaDTO.getIdEspacio())){
			throw new ReservaSolapadaException("No se ha podido crear la reserva debido a que esta solapa con otra.");
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
	public boolean esPosibleReservarEspacioEnRango(RangoDateTime rango, int idEspacio) {
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
			throw new ReservaSolapadaException("No se ha podido editar la reserva. Es posible que se solape con otra existente");
		}

		Reserva reserva = findReservaByIdReserva(reservaDTO.getId());
		reserva.setAsunto(reservaDTO.getTitle());
		reserva.setRango(new RangoDateTime(reservaDTO.getStart(), reservaDTO.getEnd()));

		return reservaRepository.save(reserva);
	}

	@Override
	public void eliminarReserva(long idReserva) {
		 reservaRepository.delete(idReserva);
	}

}
