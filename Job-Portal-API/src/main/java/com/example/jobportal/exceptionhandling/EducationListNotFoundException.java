package com.example.jobportal.exceptionhandling;

public class EducationListNotFoundException extends RuntimeException {
	private String message;

	public EducationListNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
