package com.example.jobportal.exceptionhandling;

public class UserNotFoundByUserRole extends RuntimeException {
	private String message;

	public UserNotFoundByUserRole(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		
		return message;
	}
	

}
