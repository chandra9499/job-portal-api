package com.example.jobportal.exceptionhandling;

public class SkillNotFoundByIdException extends RuntimeException {
	private String message;

	public SkillNotFoundByIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
