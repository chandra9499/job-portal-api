package com.example.jobportal.utility;

public class ErrorStructure<T> {
	private int statusCode;
	private String message;
	private T errordata;
	
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getErrordata() {
		return errordata;
	}
	public void setErrordata(T errordata) {
		this.errordata = errordata;
	}

	
	

}
