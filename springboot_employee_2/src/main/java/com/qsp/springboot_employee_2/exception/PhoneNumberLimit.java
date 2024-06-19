package com.qsp.springboot_employee_2.exception;

public class PhoneNumberLimit extends RuntimeException{
	private String message;

	public PhoneNumberLimit(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
