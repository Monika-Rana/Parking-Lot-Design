package com.parkinglot.Exception;

public class ExceptionHandler extends RuntimeException{
	
	
	private String message;
	
	public ExceptionHandler(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
