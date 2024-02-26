package com.example.jobportal.exceptionhandling;

public class ResumeNotFoundException extends RuntimeException {
	private String message;

	public ResumeNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
