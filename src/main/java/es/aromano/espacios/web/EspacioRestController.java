package es.aromano.espacios.web;

import es.aromano.espacios.web.dto.EspacioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import es.aromano.espacios.domain.model.Espacio;
import es.aromano.espacios.service.EspacioService;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class EspacioRestController {

	@Autowired
	private EspacioService espacioService;
	
	@PostMapping(value = "/activar-desactivar-espacio")
	public ResponseEntity<Void> desactivarEdificio(@RequestBody int idEspacio){
		
		Espacio espacio = espacioService.findEspacio(idEspacio);
		
		if(espacio == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		espacioService.toggleActivarEspacio(espacio);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping(value = "/info-espacio")
	public ResponseEntity<EspacioDTO> obtenerEspacio(@RequestBody int idEspacio){
		Espacio espacio = espacioService.findEspacio(idEspacio);

		if(espacio == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(EspacioDTO.from(espacio));
	}


	@PostMapping(value = "/editar-espacio")
	public ResponseEntity<Void> doEditarEspacio(@RequestBody @Valid EspacioDTO espacioDTO, BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			return ResponseEntity.notFound().build();
		}

		Espacio espacioEditado = espacioService.editarEspacio(espacioDTO);
		if(Objects.isNull(espacioEditado)){
			return ResponseEntity.notFound().build();
		}

		return  ResponseEntity.ok().build();
	}

	
}
