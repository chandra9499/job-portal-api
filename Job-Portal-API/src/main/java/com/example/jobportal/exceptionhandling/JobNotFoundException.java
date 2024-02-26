package com.example.jobportal.exceptionhandling;

public class JobNotFoundException extends RuntimeException {
	private String message;

	public JobNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
