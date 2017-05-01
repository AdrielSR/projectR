package es.aromano.espacios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.espacios.model.Espacio;
import es.aromano.espacios.repository.EspacioRepository;

@Service
public class EspacioServiceImpl implements EspacioService {

	@Autowired
	private EspacioRepository espacioRepository;
	
	@Override
	public List<Espacio> espaciosActivos() {
		return espacioRepository.findEspaciosActivos();
	}

}
