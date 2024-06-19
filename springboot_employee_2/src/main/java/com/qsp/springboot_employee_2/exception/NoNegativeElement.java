package com.qsp.springboot_employee_2.exception;

public class NoNegativeElement extends RuntimeException{
	private String  message;

	public NoNegativeElement(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}