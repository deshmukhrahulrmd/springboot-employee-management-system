package com.qsp.springboot_employee_2.exception;

public class EmployeeNotFound extends RuntimeException{
	private String message;

	public EmployeeNotFound(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
