package com.qsp.springboot_employee_2.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Employee {
	// We are generating automatically so no need to validate
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name should not be null !!!")										// Written only on String type of Data
	@NotNull(message = "Name can't be Null !!!")											// Written only on String type of Data
	private String name;
	
	@Column(unique = true)
	@Min(value = 6000000000l)																// Written only on Number type of Data
	@Max(value = 9999999999l)																// Written only on Number type of Data
	private long phone;
	
//	If we take phone number in String we have to do this validation
//	@Pattern(regexp = "[6-9][0-9]{9}", message = "Invalid Phone number")					// Written only on String type of Data
//	private String phone;
	
	@NotBlank(message = "Address should not be null !!!")
	@NotNull(message = "Address can't be Null !!!")
	private String address;
	
	@Column(unique = true)
	@NotBlank(message = "Email should not be null !!!")
	@NotNull(message = "Email can't be Null !!!")
	@Email(regexp = "[a-z0-9._$]+@[a-z]+\\.[a-z]{2,3}",message = "Invalid Email Format")	// Written only on String type of Data
	private String email;
	
	@Min(value = 1)
	private double salary;

	// We are generating internally so no need to validate
	private char grade;
}
