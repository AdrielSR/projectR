package es.aromano.edificios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.edificios.model.Edificio;
import es.aromano.edificios.repository.EdificioRepository;

@Service
public class EdificioServiceImp implements EdificioService{

	@Autowired
	private EdificioRepository edificioRepository;
	
	@Override
	public List<Edificio> edificiosEmpresaLogada() {
		return edificioRepository.edificiosEmpresaLogada();
	}


}
