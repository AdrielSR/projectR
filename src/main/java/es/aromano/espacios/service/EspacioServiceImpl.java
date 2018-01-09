package es.aromano.espacios.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.edificios.service.EdificioService;
import es.aromano.espacios.domain.model.Espacio;
import es.aromano.espacios.domain.EspacioRepository;
import es.aromano.espacios.web.dto.EspacioDTO;

@Service
public class EspacioServiceImpl implements EspacioService {

	@Autowired
	private EspacioRepository espacioRepository;
	
	@Autowired
	private EdificioService edificioService;
	
	
	@Override
	public Espacio findEspacio(int idEspacio) {
		return espacioRepository.findOne(idEspacio);
	}
	
	@Override
	public List<Espacio> espaciosActivos() {
		return espacioRepository.findEspaciosActivos();
	}

	@Override
	public List<Espacio> espaciosDesactivos() {
		return espacioRepository.findEspaciosDesactivos();
	}
	
	@Override
	public Espacio crearEspacio(EspacioDTO espacioDTO) {
		
		Espacio newEspacio = new Espacio(espacioDTO.getNombre(), espacioDTO.getAforo());
		newEspacio.setEdificio(edificioService.findEdificio(espacioDTO.getIdEdificio()));
		
		return espacioRepository.save(newEspacio);
	}

	@Override
	public Espacio editarEspacio(EspacioDTO espacioDTO) {
		Espacio espacio = espacioRepository.findOne(espacioDTO.getId());

		if(Objects.nonNull(espacio)){
			espacio.setNombre(espacioDTO.getNombre());
			espacio.setAforo(espacioDTO.getAforo());
			espacio.setEdificio(edificioService.findEdificio(espacioDTO.getIdEdificio()));
			espacio.setPrestaciones(espacioDTO.getPrestaciones());
		}

		return espacioRepository.save(espacio);
	}

	@Override
	public Espacio toggleActivarEspacio(Espacio espacio) {
		if(espacio.isActivo()){
			espacio.desactivar();
		}
		else{
			espacio.activar();
		}
		
		
		return espacioRepository.save(espacio);
	}

	@Override
	public List<Espacio> findEspaciosActivosByIdEdificio(int idEdificio) {
		return espacioRepository.findEspaciosActivosByIdEdificio(idEdificio);
	}

	@Override
	public boolean canAccessUser(int idEspacio) {
		Espacio espacio = findEspacio(idEspacio);
		
		if(espacio == null){
			return false;
		}
		
		return edificioService.canAccessUser(espacio.getEdificio().getId());
	}



}
