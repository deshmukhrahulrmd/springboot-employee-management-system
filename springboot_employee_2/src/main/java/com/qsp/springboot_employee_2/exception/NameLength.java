package com.qsp.springboot_employee_2.exception;

public class NameLength extends RuntimeException{
	private String message;

	public NameLength(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
