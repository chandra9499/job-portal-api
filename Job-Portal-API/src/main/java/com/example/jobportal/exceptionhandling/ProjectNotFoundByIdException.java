package com.example.jobportal.exceptionhandling;

public class ProjectNotFoundByIdException extends RuntimeException {
 private String message;

public ProjectNotFoundByIdException(String message) {
	super();
	this.message = message;
}

@Override
public String getMessage() {
	return message;
}
 
}
