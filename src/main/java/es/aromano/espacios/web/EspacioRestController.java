package es.aromano.espacios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.aromano.espacios.model.Espacio;
import es.aromano.espacios.service.EspacioService;

@RestController
public class EspacioRestController {

	@Autowired
	private EspacioService espacioService;
	
	@RequestMapping(value = "/activar-desactivar-espacio", method = RequestMethod.POST)
	public ResponseEntity<Void> desactivarEdificio(@RequestBody int idEspacio){
		
		Espacio espacio = espacioService.findEspacio(idEspacio);
		
		if(espacio == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		espacioService.toggleActivarEspacio(espacio);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
