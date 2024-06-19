package com.qsp.springboot_employee_2.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.springboot_employee_2.util.ResponseStructure;

@RestControllerAdvice // To handle our custom exception we use this or we can use @ControllerAdvixe
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleIdNotFound(IdNotFound idNotFound){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(idNotFound.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("No Id present in Database !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<ResponseStructure<String>> handleEmployeNotFound(EmployeeNotFound employeeNotFound) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(employeeNotFound.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("No Employee present in Database !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleEmptyDatabase(EmptyDatabase emptyDatabase) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(emptyDatabase.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Database is empty, add data first !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NameLength.class)
	public ResponseEntity<ResponseStructure<String>> handleNameLength(NameLength nameLength) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(nameLength.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("Name Length is to short, re-enter Name !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleNoNegativeElement(NoNegativeElement noNegativeElement) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(noNegativeElement.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("Negative values is not allowed, re-enter positive values !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PhoneNumberLimit.class)
	public ResponseEntity<ResponseStructure<String>> handlePhoneNumberLimit(PhoneNumberLimit phoneNumberLimit) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(phoneNumberLimit.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("Check phone number length, re-enter !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleWrongAddressFormat(WrongAddressFormat wrongAddressFormat) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(wrongAddressFormat.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("Address is too short, re-enter address !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WrongEmailFormat.class)
	public ResponseEntity<ResponseStructure<String>> handleWrongEmailFormat(WrongEmailFormat wrongEmailFormat) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(wrongEmailFormat.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("Email format is invalid, add @gmail.com !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleWrongGrade(WrongGrade wrongGrade) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(wrongGrade.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("Enter grade (A, B, C, D, E, E-, F) !!!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@Override // we can remove protected and make it public
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> allErrors = ex.getAllErrors();	// To get complete object in that all errors are present in 'ex'
		Map<String, String> map = new HashMap<String, String>();	// We have to display a Field name along with message so in java only one Object that is Map holds a data into key and value pairs so we create a 'HashMap' to store FieldName & DefaultMessage (in HashMap key not be duplicate value can be duplicate)
		for (ObjectError objectError : allErrors) {			// To iterate over object of 'ObjectError', parent of 'FieldError' is 'ObjectError'
			FieldError fieldError = (FieldError)objectError;	// so we need to downcast ObjectError to 'FieldError'
			String fieldName = fieldError.getField();		// To get Name of field from fieldError where error occur
			String message = fieldError.getDefaultMessage();
			
			map.put(fieldName, message);		// To add data into Map object we created
		}
		
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
}









