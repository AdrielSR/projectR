package es.aromano.empresas.service;

import es.aromano.empresas.domain.exceptions.EmpresaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.aromano.empresas.domain.model.Empresa;
import es.aromano.empresas.domain.EmpresaRepository;

import java.util.Objects;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Empresa findById(int idEmpresa) {
		return empresaRepository.findOne(idEmpresa);
	}

	@Override
	public Empresa createEmpresa(Empresa empresa) throws EmpresaException {
		Empresa emp = empresaRepository.findEmpresaByNombre(empresa.getNombre());

		if(Objects.isNull(emp)){
			emp = empresaRepository.findEmpresaByCif(empresa.getCif());
		}

		if(Objects.nonNull(emp)){
			throw new EmpresaException("Lo sentimos. Ya existe una empresa con ese nombre o cif");
		}

		Empresa newEmpresa = new Empresa(empresa.getNombre(), empresa.getCif());
		
		return empresaRepository.save(newEmpresa);
	}

}
