package com.example.jobportal.exceptionhandling;

public class JobNotFoundByIdException extends RuntimeException {
	private String message;

	public JobNotFoundByIdException(String message) {
		super(message);
		this.message = message;
	}
	

}
