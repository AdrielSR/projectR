package es.aromano.espacios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.edificios.service.EdificioService;
import es.aromano.espacios.model.Espacio;
import es.aromano.espacios.repository.EspacioRepository;
import es.aromano.espacios.web.EspacioDTO;

@Service
public class EspacioServiceImpl implements EspacioService {

	@Autowired
	private EspacioRepository espacioRepository;
	
	@Autowired
	private EdificioService edificioService;
	
	@Override
	public List<Espacio> espaciosActivos() {
		return espacioRepository.findEspaciosActivos();
	}

	@Override
	public Espacio crearEspacio(EspacioDTO espacioDTO) {
		
		Espacio newEspacio = new Espacio(espacioDTO.getNombre(), espacioDTO.getAforo());
		newEspacio.setEdificio(edificioService.findEdificio(espacioDTO.getIdEdificio()));
		
		return espacioRepository.save(newEspacio);
	}

}
