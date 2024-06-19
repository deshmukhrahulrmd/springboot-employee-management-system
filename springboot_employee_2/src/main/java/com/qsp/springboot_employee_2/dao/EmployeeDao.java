package com.qsp.springboot_employee_2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_employee_2.dto.Employee;
import com.qsp.springboot_employee_2.repository.EmployeeRepository;

@Repository 	// it will work in such a way that work of this class is only to talk with Repository
public class EmployeeDao {
	
	@Autowired
	private EmployeeRepository repository;
	
	//************************************************** To save the employee
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
	
	//**************************************************To find the employee by Id
	public Employee findemployeeById(int id) {
//		return repository.findById(id).get();	 It will not work if data is not present is database
		// To avoid NoSuchElementException
		Optional<Employee> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
		
//		if (optional.isEmpty()) {
//			return null;
//		}
//		return optional.get();
		
	}
	
	//**************************************************To find all employee present in database
	public List<Employee> findAllEmployees(){
		return repository.findAll();
	}
	
	//**************************************************To delete a employee present in database	(Logic By Me)
//	public String deleteEmployeeById(int id) {
//		Optional<Employee> optional = repository.findById(id);
//		if (optional.isPresent()) {
//			repository.deleteById(id);
//			return "Deleted Successfully !!!";
//		} else {
//			return "Data is not there !!!";
//		}
//	}
	
	//**************************************************To delete a employee present in database	(Logic By Sir)		// To delete there are 2 methods (1. deleteById(int)  2.delete(Entity object))
//	public Employee deleteEmployeeById(int id) {
//		Optional<Employee> optional = repository.findById(id);
//		if (optional.isPresent()) {
////			repository.deleteById(id);
////			return optional.get();
//			Employee dbEmployee = optional.get();
//			repository.delete(dbEmployee);
//			return dbEmployee;
//		}
//		return null;
//	}
	
	public Employee deleteEmployeeById(Employee employee) {
		repository.delete(employee);
		return employee;
	}

	//**************************************************To update whole data of employee
//	public Employee udapteAllData(int id, Employee employee) {
//		Optional<Employee> optional = repository.findById(id);
//		if (optional.isPresent()) {
////			employee.setId(optional.get().getId());		// This is also right
//			employee.setId(id); // This is also right  because by using this id we get optional object.
//			return repository.save(employee);
//		}
//		return null;
//	}

	//**************************************************To save multiple employees at a time
	public List<Employee> saveAllEMployees(List<Employee> empList) {
		return repository.saveAll(empList);
	}

	//Start**************************************************To partial updation details of employee
	//**************************************************To update phone of employee
//	public Employee updatePhone(int id, long phone) {
//		Optional<Employee> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Employee dbEmployee = optional.get();
//			dbEmployee.setPhone(phone);
//			return repository.save(dbEmployee);
//		}
//		return null;
//	}
	
	//**************************************************To update email of employee
//	public Employee updateEmail(int id, String email) {
//		Optional<Employee> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setEmail(email);
//			return repository.save(employee);
//		}
//		return null;
//	}
	
	//**************************************************To update name of employee
//	public Employee updateName(int id, String name) {
//		Optional<Employee> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setName(name);
//			return repository.save(employee);
//		}
//		return null;
//	}
	
	//**************************************************To update Address of employee
//	public Employee updateAddress(int id, String address) {
//		Optional<Employee> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setAddress(address);
//			return repository.save(employee);
//		}
//		return null;
//	}
	
	//**************************************************To update Salary of employee
//	public Employee updateSalary(int id, double salary) {
//		Optional<Employee> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setSalary(salary);
//			return repository.save(employee);
//		}
//		return null;
//	}
	//End**************************************************To partial updation details of employee

	//**************************************************To Find employee by (unique)phone
	public Employee findByPhone(long phone) {
//		Employee employee = repository.findEmployeeByPhone(phone);
		//  To avoid NoSuchElementException we  the coming data
//		if (employee!=null){
//			return employee;
//		}
//		return null;
		
		return repository.findEmployeeByPhone(phone);
	}

	//**************************************************To Find employee by (unique)email
	public Employee findByEmail(String email) {
//		Employee employee = repository.getEmployeeByEmail(email);
	//  To avoid NoSuchElementException we  the coming data
//		if (employee!=null){
//			return employee;
//		}
//		return null;
		return repository.getEmployeeByEmail(email);
	}

	//**************************************************To Find employee by address
	public List<Employee> employeesByAddress(String address) {
		return repository.employeesByAddress(address);
	}

	//**************************************************To Find employee by name
	public List<Employee> employeesByName(String name) {
		return repository.employeesByName(name);
	}
	
	//**************************************************To Find employees who's salary is Less Than  give salary
	public List<Employee> employeesSalaryLessThan(double salary){
		return repository.findEmployeesBySalaryLessThan(salary);
	}
	
	//**************************************************To Find employees who's salary is Greater Than  give salary
	public List<Employee> employeesSalarygreaterThan(double salary){
		return repository.findEmployeesBySalaryGreaterThan(salary);
	}
	
	//**************************************************To Find employees who's salary is Between give range
	public List<Employee> employeesSalaBetween(double salary1, double salary2){
		return repository.empSalBetween(salary1, salary2);
	}

	//**************************************************To Find employees by grade
	public List<Employee> employeesByGrade(char grade) {
		return repository.getAllEmployeesByGrade(grade);
	}
}
