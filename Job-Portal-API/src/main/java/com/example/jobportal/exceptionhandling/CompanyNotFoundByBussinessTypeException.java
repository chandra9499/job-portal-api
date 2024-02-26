package com.example.jobportal.exceptionhandling;

public class CompanyNotFoundByBussinessTypeException extends RuntimeException {
	
	private String mess;

	public CompanyNotFoundByBussinessTypeException(String mess) {
		
		this.mess = mess;
	}

	@Override
	public String getMessage() {
		return mess;
	}
}
