package es.aromano.reservas.service;

import java.util.List;

import es.aromano.espacios.service.EspacioService;
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
		return reservaRepository.reservasUsuario();
	}

	@Override
	public List<Reserva> findReservasByIdEspacio(int idEspacio) {
		return reservaRepository.findReservasByIdEspacio(idEspacio);
	}

	@Override
	public Reserva crearReserva(ReservaDTO reservaDTO) {
		Reserva newReserva = ReservaStepBuilder.builder()
								.propietario(userService.getCurrentUser())
								.lugar(espacioService.findEspacio(reservaDTO.getIdEspacio()))
								.desde(reservaDTO.getStart())
								.hasta(reservaDTO.getEnd())
								.asunto(reservaDTO.getTitle())
								.build();


		return reservaRepository.save(newReserva);
	}

}
