package com.example.jobportal.exceptionhandling;

public class IllegalAccessException extends RuntimeException {
	
	private String mess;

	public IllegalAccessException(String mess) {
		
		this.mess = mess;
	}

	@Override
	public String getMessage() {
		return mess;
	}

	
}
