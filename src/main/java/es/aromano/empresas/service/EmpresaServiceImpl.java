package es.aromano.empresas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.empresas.model.Empresa;
import es.aromano.empresas.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Empresa findById(int idEmpresa) {
		return empresaRepository.findOne(idEmpresa);
	}

}
