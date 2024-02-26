package com.example.jobportal.exceptionhandling;

public class UserNotFoundException extends RuntimeException {

	private String mess;

	public UserNotFoundException(String mess) {
		this.mess = mess;
	}

	@Override
	public String getMessage() {
		return mess;
	}

}
