package com.victorvaz.workshopmongo.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.victorvaz.workshopmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice //Indica que essa classe trata possiveis exceptions
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		/*status.value() -> converte para numero inteiro*/
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontradp", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}