package es.aromano.espacios.service;

import java.util.List;

import es.aromano.espacios.model.Espacio;
import es.aromano.espacios.web.EspacioDTO;

public interface EspacioService {


	List<Espacio> espaciosActivos();

	Espacio crearEspacio(EspacioDTO espacioDTO);
	
}
