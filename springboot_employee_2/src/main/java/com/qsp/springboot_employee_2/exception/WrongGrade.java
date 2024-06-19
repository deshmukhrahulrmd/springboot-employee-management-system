package com.qsp.springboot_employee_2.exception;

public class WrongGrade extends RuntimeException{
	private String message;

	public WrongGrade(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
