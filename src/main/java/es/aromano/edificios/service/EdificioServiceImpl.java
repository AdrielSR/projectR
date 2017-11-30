package es.aromano.edificios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import es.aromano.edificios.web.dto.EdificioDTO;
import es.aromano.storage.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.edificios.domain.model.Edificio;
import es.aromano.edificios.domain.EdificioRepository;
import es.aromano.users.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EdificioServiceImpl implements EdificioService{

	@Autowired
	private EdificioRepository edificioRepository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private StorageService storageService;
	
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
	public Edificio editarEdificio(int idEdificio, EdificioDTO e) {
		
		Edificio edificio = findEdificioActivo(idEdificio);
		
		if(edificio == null){
			throw new IllegalArgumentException(String.format("No se ha encontrado el edificio con el id=[%d]", idEdificio));
		}
		
		edificio.setNombre(e.getNombre());
		edificio.setDireccion(e.getDireccion());

		String fullPath = "";
		if(!e.getFile().isEmpty()){
			fullPath = generarFullPathFichero(e.getFile(), idEdificio);
			storageService.store(e.getFile(), fullPath);
		}

		edificio.setEnlaceImagen(fullPath);
		
		
		return edificioRepository.save(edificio);
	}


	private String generarFullPathFichero(MultipartFile file, int idEdificio){
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileNameCopy = String.format("%s.%s", UUID.randomUUID().toString(), fileExtension);


		return String.format("/uploads/edificio/%d/%s", idEdificio, fileNameCopy);
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
