package com.example.jobportal.exceptionhandling;

public class CompanyNotFoundByLocation extends RuntimeException {
	private String message;

	public CompanyNotFoundByLocation(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	

}
