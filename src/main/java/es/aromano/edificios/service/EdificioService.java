package es.aromano.edificios.service;

import java.util.List;

import es.aromano.edificios.model.Edificio;
import es.aromano.users.model.User;

public interface EdificioService {

	List<Edificio> edificiosActivos();
	
	List<Edificio> edificiosDesactivos();

	Edificio crearEdificio(Edificio edificio);

	Edificio findEdificioActivo(int idEdificio);

	Edificio findEdificio(int idEdificio);
	
	Edificio editarEdificio(int idEdificio, Edificio edificio);

	Edificio toggleActivarEdificio(Edificio edificio);

	boolean canAccessUser(int id);
}
