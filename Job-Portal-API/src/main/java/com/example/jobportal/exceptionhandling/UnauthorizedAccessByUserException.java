package com.example.jobportal.exceptionhandling;

public class UnauthorizedAccessByUserException extends RuntimeException {
	private String message;

	public UnauthorizedAccessByUserException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
