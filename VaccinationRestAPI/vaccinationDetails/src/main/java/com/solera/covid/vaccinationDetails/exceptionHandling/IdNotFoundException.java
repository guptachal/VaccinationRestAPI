package com.solera.covid.vaccinationDetails.exceptionHandling;

public class IdNotFoundException extends RuntimeException{

	public IdNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	public IdNotFoundException(String message) {
		super(message);
	}
	public IdNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
