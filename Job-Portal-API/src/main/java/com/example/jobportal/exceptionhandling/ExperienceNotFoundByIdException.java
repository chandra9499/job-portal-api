package com.example.jobportal.exceptionhandling;

public class ExperienceNotFoundByIdException extends RuntimeException {
	private String message;

	public ExperienceNotFoundByIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
