package com.example.jobportal.exceptionhandling;

public class CompanyNotFoundException extends RuntimeException {
	private String message;

	public CompanyNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	

}
