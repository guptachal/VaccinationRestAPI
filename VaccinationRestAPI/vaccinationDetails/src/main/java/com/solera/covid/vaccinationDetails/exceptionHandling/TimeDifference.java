package com.solera.covid.vaccinationDetails.exceptionHandling;

public class TimeDifference extends RuntimeException {

	public TimeDifference() {
		// TODO Auto-generated constructor stub
	}
	public TimeDifference(String message) {
		super(message);
	}
	public TimeDifference(Throwable cause) {
		super(cause);
	}
}
