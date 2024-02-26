package com.example.jobportal.exceptionhandling;

public class JobNotFoundByTitleException extends RuntimeException {
	private String message;

	public JobNotFoundByTitleException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	

}
