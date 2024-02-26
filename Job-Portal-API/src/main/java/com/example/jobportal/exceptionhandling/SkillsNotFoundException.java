package com.example.jobportal.exceptionhandling;

public class SkillsNotFoundException extends RuntimeException {
	private String message;

	public SkillsNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
