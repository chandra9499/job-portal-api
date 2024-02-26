package com.example.jobportal.exceptionhandling;

public class ProjectNotFoundException extends RuntimeException {
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public ProjectNotFoundException(String message) {
		super();
		this.message = message;
	}

}
