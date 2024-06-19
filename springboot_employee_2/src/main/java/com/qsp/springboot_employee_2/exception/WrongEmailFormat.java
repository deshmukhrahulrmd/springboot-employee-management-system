package com.qsp.springboot_employee_2.exception;

public class WrongEmailFormat extends RuntimeException{
	private String message;

	public WrongEmailFormat(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
