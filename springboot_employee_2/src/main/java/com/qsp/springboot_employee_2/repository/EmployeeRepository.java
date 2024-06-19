package com.qsp.springboot_employee_2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.springboot_employee_2.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	// Here find act as SELETE clause & by act as WHERE clause, so after find we have to use same class name of Entity class and we have to follow convention
	// Implementation is give by Spring boot if we use (find By) / (get By)
	Employee findEmployeeByPhone(long phone);
	Employee getEmployeeByEmail(String email);
	
	@Query("SELECT emp FROM Employee emp WHERE emp.address=?1")		// To give our own implementation to the method
	List<Employee> employeesByAddress(String address);
	
	@Query("SELECT emp FROM Employee emp WHERE emp.name=?1")
	List<Employee> employeesByName(String name);
	
	List<Employee> findAllEmployeeByName(String name);
	
	List<Employee> findEmployeesBySalaryLessThan(double salary);
	
	List<Employee> findEmployeesBySalaryGreaterThan(double salary);
	
	@Query("SELECT emp FROM Employee emp WHERE emp.salary>=?1 AND emp.salary<=?2")
	List<Employee> empSalBetween(double salary1, double salary2);
	
//	@Query("SELECT emp FROM Employee emp WHERE emp.salary BETWEEN ?1 AND ?2")
//	List<Employee> empSalBetween(double salary1, double salary2);
	
	List<Employee> getAllEmployeesByGrade(char grade);
}
