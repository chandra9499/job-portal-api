package com.example.jobportal.exceptionhandling;

public class SocialProfileNotFoundByIdException extends RuntimeException {
	private String message;

	public SocialProfileNotFoundByIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
}
