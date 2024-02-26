package com.example.jobportal.exceptionhandling;

public class JobNotFoundByCompanyIdException extends RuntimeException {
	private String message;

	public JobNotFoundByCompanyIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
