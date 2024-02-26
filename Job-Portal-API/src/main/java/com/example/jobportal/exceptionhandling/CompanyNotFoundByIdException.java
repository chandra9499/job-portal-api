package com.example.jobportal.exceptionhandling;

public class CompanyNotFoundByIdException extends RuntimeException {
	private String message;

	public CompanyNotFoundByIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	

}
