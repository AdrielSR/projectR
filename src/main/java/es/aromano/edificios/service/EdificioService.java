package es.aromano.edificios.service;

import java.util.List;
import es.aromano.edificios.domain.model.Edificio;
import es.aromano.edificios.web.dto.EdificioDTO;

public interface EdificioService {

	List<Edificio> edificiosActivos();
	
	List<Edificio> edificiosDesactivos();

	Edificio crearEdificio(Edificio edificio);

	Edificio findEdificioActivo(int idEdificio);

	Edificio findEdificio(int idEdificio);
	
	Edificio editarEdificio(int idEdificio, EdificioDTO edificio);

	Edificio toggleActivarEdificio(Edificio edificio);

	boolean canAccessUser(int idEdificio);
}
