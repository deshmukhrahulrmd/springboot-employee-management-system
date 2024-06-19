package com.qsp.springboot_employee_2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springboot_employee_2.dao.EmployeeDao;
import com.qsp.springboot_employee_2.dto.Employee;
import com.qsp.springboot_employee_2.service.EmployeeService;
import com.qsp.springboot_employee_2.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/employee")	--> URL:- http://localhost:8080/employee/save
//								--> URL:- http://localhost:8080/employee/fetch   like this so on
public class EmployeeController {
	
//	@Autowired
//	private EmployeeDao employeeDao;*
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/save")		// To save the employee
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/fetch")		// To find the employee by Id
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(@RequestParam int id) {
		return employeeService.findemployeeById(id);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployees(){
		return employeeService.findAllEmployees();
	}
//	
////	@DeleteMapping("/delete")			// logic by Me
////	public String deleteEmployeeById(@RequestParam int id) {
////		return employeeService.deleteEmployeeById(id);
////	}
//	
	@DeleteMapping("/delete/{id}")		// logic by Sir
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeById(@PathVariable int id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Employee>> udapteAllData(@RequestParam int id, @RequestBody Employee employee) {
		return employeeService.udapteAllData(id,employee);
	}
	
	@PostMapping("/saveall")
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployees(@RequestBody List<Employee> empList){
		return employeeService.saveAllEMployees(empList);
	}
	
	@PatchMapping("/updatephone")
	public ResponseEntity<ResponseStructure<Employee>> updatePhone(@RequestParam int id, @RequestParam long phone) {
		return employeeService.updatePhone(id,phone);
	}
	
	@PatchMapping("/updateemail")
	public ResponseEntity<ResponseStructure<Employee>> updatePhone(@RequestParam int id, @RequestParam String email) {
		return employeeService.updateEmail(id,email);
	}
	
	@PatchMapping("/updatename")
	public ResponseEntity<ResponseStructure<Employee>> updateName(@RequestParam int id, @RequestParam String name) {
		return employeeService.updateName(id,name);
	}
	
	@PatchMapping("/updateaddress")
	public ResponseEntity<ResponseStructure<Employee>> updateAddress(@RequestParam int id, @RequestParam String address) {
		return employeeService.updateAddress(id,address);
	}
	
	@PatchMapping("/updatesalary")
	public ResponseEntity<ResponseStructure<Employee>> updateSalary(@RequestParam int id, @RequestParam double salary) {
		return employeeService.updateSalary(id,salary);
	}
	
	@GetMapping("/findbyphone")
	public ResponseEntity<ResponseStructure<Employee>> findByPhone(@RequestParam long phone) {
		return employeeService.findByPhone(phone);
	}
	
	@GetMapping("/findbyemail")
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(@RequestParam String email) {
		return employeeService.findByEmail(email);
	}
	
	@GetMapping("/employeesbyaddress")
	public ResponseEntity<ResponseStructure<List<Employee>>> employeesByAddress(@RequestParam String address){
		return employeeService.employeesByAddress(address);
	}
	
	@GetMapping("/employeesbyname")
	public ResponseEntity<ResponseStructure<List<Employee>>> employeesByName(@RequestParam String name){
		return employeeService.employeesByName(name);
	}
	
	@GetMapping("/sallessthan")
	public ResponseEntity<ResponseStructure<List<Employee>>> employeesSalaryLessThan(@RequestParam double salary){
		return employeeService.employeesSalaryLessThan(salary);
	}
	
	@GetMapping("/salgreaterthan")
	public ResponseEntity<ResponseStructure<List<Employee>>> employeesSalaryGreaterThan(@RequestParam double salary){
		return employeeService.employeesSalarygreaterThan(salary);
	}
	
	@GetMapping("/salbetween")
	public ResponseEntity<ResponseStructure<List<Employee>>> employeesSalaryBetween(@RequestParam double salary1, @RequestParam double salary2){
		return employeeService.employeesSalaBetween(salary1, salary2);
	}
	
	@GetMapping("bygrade")
	public ResponseEntity<ResponseStructure<List<Employee>>> employeesByGrade(@RequestParam char grade){
		return employeeService.employeesByGrade(grade);
	}
}
