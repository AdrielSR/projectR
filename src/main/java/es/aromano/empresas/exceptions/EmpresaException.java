package es.aromano.empresas.exceptions;

public class EmpresaException extends Exception{

	public EmpresaException(String msg){
		super(msg);
	}
	
	public EmpresaException(String msg, Throwable cause){
		super(msg, cause);
	}
	
}
