package es.aromano.empresas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.empresas.domain.model.Empresa;
import es.aromano.empresas.domain.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Empresa findById(int idEmpresa) {
		return empresaRepository.findOne(idEmpresa);
	}

	@Override
	public Empresa createEmpresa(Empresa empresa) {
		Empresa newEmpresa = new Empresa(empresa.getNombre(), empresa.getCif());
		
		return empresaRepository.save(newEmpresa);
	}

}
