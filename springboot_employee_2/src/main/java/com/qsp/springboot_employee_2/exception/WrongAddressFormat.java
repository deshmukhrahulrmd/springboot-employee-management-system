package com.qsp.springboot_employee_2.exception;

public class WrongAddressFormat extends RuntimeException{
	private String message;

	public WrongAddressFormat(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
