package es.aromano.espacios.service;

import java.util.List;

import es.aromano.espacios.model.Espacio;
import es.aromano.espacios.web.EspacioDTO;

public interface EspacioService {

	Espacio findEspacio(int idEspacio);

	List<Espacio> espaciosActivos();

	List<Espacio> espaciosDesactivos();
	
	Espacio crearEspacio(EspacioDTO espacioDTO);

	Espacio toggleActivarEspacio(Espacio espacio);
	
}
