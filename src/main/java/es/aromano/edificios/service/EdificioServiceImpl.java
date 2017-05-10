package es.aromano.edificios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.edificios.model.Edificio;
import es.aromano.edificios.repository.EdificioRepository;
import es.aromano.users.service.UserService;

@Service
public class EdificioServiceImpl implements EdificioService{

	@Autowired
	private EdificioRepository edificioRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Edificio> edificiosActivos() {
		return edificioRepository.edificiosActivos();
	}
	
	@Override
	public List<Edificio> edificiosDesactivos() {
		return edificioRepository.edificiosDesactivos();
	}

	@Override
	public Edificio crearEdificio(Edificio e) {
		Edificio newEdificio = new Edificio(e.getNombre(), e.getDireccion());
		newEdificio.setEmpresa(userService.getCurrentUser().getEmpresa());
		
		return edificioRepository.save(newEdificio);
	}

	@Override
	public Edificio findEdificioActivo(int idEdificio) {
		return edificioRepository.findEdificioActivo(idEdificio);
	}
	
	@Override
	public Edificio findEdificio(int idEdificio) {
		return edificioRepository.findOne(idEdificio);
	}

	@Override
	public Edificio editarEdificio(int idEdificio, Edificio e) {
		
		Edificio edificio = findEdificioActivo(idEdificio);
		
		if(edificio == null){
			
		}
		
		edificio.setNombre(e.getNombre());
		edificio.setDireccion(e.getDireccion());
		
		
		return edificioRepository.save(edificio);
	}

	@Override
	public Edificio toggleActivarEdificio(Edificio edificio) {
		if(edificio.isActivo()){
			edificio.desactivar();
		}
		else{
			edificio.activar();
		}
		
		return edificioRepository.save(edificio);
	}

	@Override
	public boolean canAccessUser(int idEdificio) {
		return edificioRepository.edificiosActivos()
				.stream()
				.anyMatch(e -> e.getId() == idEdificio);
	}


}
