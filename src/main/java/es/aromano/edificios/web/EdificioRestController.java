package es.aromano.edificios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.aromano.edificios.domain.model.Edificio;
import es.aromano.edificios.service.EdificioService;

@RestController
public class EdificioRestController {

	@Autowired
	private EdificioService edificioService;
	
	@RequestMapping(value = "/activar-desactivar-edificio", method = RequestMethod.POST)
	public ResponseEntity<Void> desactivarEdificio(@RequestBody int idEdificio){
		
		Edificio edificio = edificioService.findEdificio(idEdificio);
		
		if(edificio == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		edificioService.toggleActivarEdificio(edificio);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
}
