package com.victorvaz.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/*RuntimeException -> o compilador não exige que voce trate
	 * sera feita uma classe auxiliar para tratar exceção */
	
	
	/*Repassa a chamada para super classe RuntimeException*/
	public ObjectNotFoundException(String msg) {
		super(msg);
	}	
}
