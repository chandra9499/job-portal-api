package com.example.jobportal.exceptionhandling;

public class SkillNotFoundByNameException extends RuntimeException {
	private String message;

	public SkillNotFoundByNameException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}	
}
