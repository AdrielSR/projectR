package es.aromano.empresas.domain.exceptions;

import es.aromano.users.domain.exceptions.UserException;

public class EmpresaException extends UserException {

	public EmpresaException(String msg){
		super(msg);
	}
	
	public EmpresaException(String msg, Throwable cause){
		super(msg, cause);
	}
	
}
