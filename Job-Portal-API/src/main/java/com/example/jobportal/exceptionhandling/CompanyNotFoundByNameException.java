package com.example.jobportal.exceptionhandling;

public class CompanyNotFoundByNameException extends RuntimeException {
	private String message;

	public CompanyNotFoundByNameException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	

}
