package com.example.spring.exception;

public class VacioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public VacioException() {
		super("No puede quedar ningún campo vacío");
	}
	
	
	

}
