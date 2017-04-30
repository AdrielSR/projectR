package es.aromano.edificios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.edificios.model.Edificio;
import es.aromano.edificios.repository.EdificioRepository;
import es.aromano.users.service.UserService;

@Service
public class EdificioServiceImp implements EdificioService{

	@Autowired
	private EdificioRepository edificioRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Edificio> edificiosActivos() {
		return edificioRepository.edificiosActivos();
	}

	@Override
	public Edificio crearEdificio(Edificio e) {
		Edificio newEdificio = new Edificio(e.getNombre(), e.getDireccion());
		newEdificio.setEmpresa(userService.getCurrentUser().getEmpresa());
		
		return edificioRepository.save(newEdificio);
	}

	@Override
	public Edificio findEdificio(int idEdificio) {
		return edificioRepository.findEdificio(idEdificio);
	}

	@Override
	public Edificio editarEdificio(int idEdificio, Edificio e) {
		
		Edificio edificio = findEdificio(idEdificio);
		
		if(edificio == null){
			
		}
		
		edificio.setNombre(e.getNombre());
		edificio.setDireccion(e.getDireccion());
		
		
		return edificioRepository.save(edificio);
	}


}
