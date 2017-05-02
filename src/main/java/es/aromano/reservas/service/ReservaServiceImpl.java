package es.aromano.reservas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.reservas.model.Reserva;
import es.aromano.reservas.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Override
	public List<Reserva> reservasUsuario() {
		return reservaRepository.reservasUsuario();
	}

}
