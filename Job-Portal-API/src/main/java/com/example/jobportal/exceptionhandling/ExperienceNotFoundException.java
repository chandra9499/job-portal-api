package com.example.jobportal.exceptionhandling;

public class ExperienceNotFoundException extends RuntimeException {
	private String message;

	public ExperienceNotFoundException(String mesage) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
