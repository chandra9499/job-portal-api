package com.example.jobportal.exceptionhandling;

public class ResumesNotFoundBySkillException extends RuntimeException {
	private String message;

	public ResumesNotFoundBySkillException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
