package com.example.jobportal.exceptionhandling;

public class ResumeNotFoundByIdException extends RuntimeException {
	private String message;

	public ResumeNotFoundByIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {

		return message;
	}
}
