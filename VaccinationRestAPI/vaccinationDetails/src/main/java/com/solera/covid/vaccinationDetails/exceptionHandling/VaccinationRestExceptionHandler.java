package com.solera.covid.vaccinationDetails.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VaccinationRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<VaccinationErrorResponse> handleException(IdNotFoundException e){
		VaccinationErrorResponse ver = new VaccinationErrorResponse(HttpStatus.NOT_FOUND.value(),
																	e.getMessage(),
																	System.currentTimeMillis());
		return new ResponseEntity<>(ver,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<VaccinationErrorResponse> handleException(Exception e){
		VaccinationErrorResponse ver = new VaccinationErrorResponse(HttpStatus.BAD_REQUEST.value(),
																	e.getMessage(),
																	System.currentTimeMillis());
		return new ResponseEntity<>(ver,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler
	public ResponseEntity<VaccinationErrorResponse> handleException(TimeDifference e){
		VaccinationErrorResponse ver = new VaccinationErrorResponse(HttpStatus.BAD_GATEWAY.value(),
																	e.getMessage(),
																	System.currentTimeMillis());
		return new ResponseEntity<>(ver,HttpStatus.BAD_GATEWAY);
		
	}
	@ExceptionHandler
	public ResponseEntity<VaccinationErrorResponse> handleException(GenericException e){
		VaccinationErrorResponse ver = new VaccinationErrorResponse(HttpStatus.BAD_GATEWAY.value(),
																	e.getMessage(),
																	System.currentTimeMillis());
		return new ResponseEntity<>(ver,HttpStatus.BAD_GATEWAY);
		
	}
	
	
	
}
