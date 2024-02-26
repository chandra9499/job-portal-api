package com.example.jobportal.exceptionhandling;

public class EducationNotFoundByIdException extends RuntimeException {
	private String message;

	public EducationNotFoundByIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
