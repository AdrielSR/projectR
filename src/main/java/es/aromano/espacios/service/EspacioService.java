package es.aromano.espacios.service;

import java.util.List;

import es.aromano.espacios.domain.model.Espacio;
import es.aromano.espacios.web.dto.EspacioDTO;

public interface EspacioService {

	Espacio findEspacio(int idEspacio);

	List<Espacio> espaciosActivos();

	List<Espacio> espaciosDesactivos();
	
	Espacio crearEspacio(EspacioDTO espacioDTO);

	Espacio editarEspacio(EspacioDTO espacioDTO);

	Espacio toggleActivarEspacio(Espacio espacio);
	
	List<Espacio> findEspaciosActivosByIdEdificio(int idEdificio);
	
	boolean canAccessUser(int idEspacio);
}
