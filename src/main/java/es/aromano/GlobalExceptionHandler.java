package es.aromano;

import es.aromano.reservas.domain.excepciones.ReservaSolapadaException;
import es.aromano.reservas.web.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.aromano.empresas.domain.exceptions.EmpresaException;
import es.aromano.users.domain.exceptions.UserException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = EmpresaException.class)
	public String handlerEmpresaException(EmpresaException e){
		return e.getMessage();
	}
	
	@ExceptionHandler(value = UserException.class)
	public String handlerUserException(UserException e){
		return e.getMessage();
	}

	@ExceptionHandler(value = ReservaSolapadaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handlerReservaSolapadaException(ReservaSolapadaException e){
		return new ErrorResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	
}
