package es.aromano.empresas.service;

import es.aromano.empresas.model.Empresa;

public interface EmpresaService {

	Empresa findById(int idEmpresa);
	
	Empresa createEmpresa(Empresa empresa);
}
