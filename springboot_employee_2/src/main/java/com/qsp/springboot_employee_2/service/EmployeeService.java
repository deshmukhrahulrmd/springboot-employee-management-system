package com.qsp.springboot_employee_2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_employee_2.dao.EmployeeDao;
import com.qsp.springboot_employee_2.dto.Employee;
import com.qsp.springboot_employee_2.exception.WrongEmailFormat;
import com.qsp.springboot_employee_2.exception.WrongGrade;
import com.qsp.springboot_employee_2.exception.IdNotFound;
import com.qsp.springboot_employee_2.exception.NameLength;
import com.qsp.springboot_employee_2.exception.NoNegativeElement;
import com.qsp.springboot_employee_2.exception.PhoneNumberLimit;
import com.qsp.springboot_employee_2.exception.WrongAddressFormat;
import com.qsp.springboot_employee_2.exception.EmptyDatabase;
import com.qsp.springboot_employee_2.exception.EmployeeNotFound;
import com.qsp.springboot_employee_2.util.ResponseStructure;

@Service // it will work in such a way that work of this class is only to write business
			// logic & it should act as mediator between Controller class & Dao class
public class EmployeeService {
	@Autowired
	private EmployeeDao dao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		double sal = employee.getSalary();
		if (sal < 10000) {
			employee.setGrade('D');
		} else if (sal >= 10000 && sal < 20000) {
			employee.setGrade('C');
		} else if (sal >= 20000 && sal < 40000) {
			employee.setGrade('B');
		} else {
			employee.setGrade('A');
		}

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("Save Successful !!!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveEmployee(employee)); // It the data get save then only we get object of emp so we
														// first save the emp and then pass it to structure.setData();
//		return structure;
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> findemployeeById(int id) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			Employee dbEmployee = dao.findemployeeById(id);
			if (dbEmployee != null) {
				structure.setMessage("Found successful");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbEmployee);
//				return structure;
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("Employee with Id- "+id+" not Found !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbEmployee);
//				return structure;
				throw new IdNotFound(id+" Id not found !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployees() {
		List<Employee> dbEmpList = dao.findAllEmployees();
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (dbEmpList.isEmpty()) {
//			structure.setMessage("No Data In The DataBase");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(dbEmpList);
//			return structure;
			throw new EmptyDatabase("No Employee Present in Database !!!");
		} else {
			structure.setMessage("Data Found !!!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbEmpList);
//			return structure;
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeById(int id) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			Employee dbEmployee = dao.findemployeeById(id);
			if (dbEmployee != null) {
				structure.setMessage("Deleted Successful");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.deleteEmployeeById(dbEmployee));
//				return structure;
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			} else {
//				structure.setMessage("Employee with Id- "+id+" not Found !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbEmployee);
//				return structure;
				throw new IdNotFound(id+" Id not found !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> udapteAllData(int id, Employee employee) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			Employee dbEmployee = dao.findemployeeById(id);
			if (dbEmployee != null) {
				employee.setId(id);
				double sal = employee.getSalary();
				if (sal < 10000) {
					employee.setGrade('D');
				} else if (sal >= 10000 && sal < 20000) {
					employee.setGrade('C');
				} else if (sal >= 20000 && sal < 40000) {
					employee.setGrade('B');
				} else {
					employee.setGrade('A');
				}
//				return dao.saveEmployee(employee);
//				employee.setId(id);
				structure.setMessage("All Data Updated Successful");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveEmployee(employee));
//				return structure;
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			} else {
//				structure.setMessage("Employee with Id- "+id+" not Found !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbEmployee);
//				return structure;
				throw new IdNotFound(id+" Id not found !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEMployees(List<Employee> empList) {
		for (Employee employee : empList) {
			double sal = employee.getSalary();
			if (sal < 10000) {
				employee.setGrade('D');
			} else if (sal >= 10000 && sal < 20000) {
				employee.setGrade('C');
			} else if (sal >= 20000 && sal < 40000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
		}
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		structure.setMessage("All Data Saved !!!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveAllEMployees(empList)); // It the data get save then only we get object of emp so we
														// first save the emp and then pass it to structure.setData();
//		return structure;
		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			int temp=0;
			for (long i = phone; i!=0; i/=10) {
				long rem = i%10;
				temp++;
			}
			if (temp==10) {
				Employee dbEmployee = dao.findemployeeById(id);
				if (dbEmployee != null) {
					dbEmployee.setId(id);
					dbEmployee.setPhone(phone);
					structure.setMessage("Phone Updated Successfully");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveEmployee(dbEmployee));
//					return structure;
					return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("Employee with Id- "+id+" not Found !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbEmployee);
//					return structure;
					 throw new IdNotFound(id+" Id not found !!!");
				}
			} else {
				throw new PhoneNumberLimit("Check Phone number length");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}		
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			if (email.endsWith("@gmail.com")) {
				Employee dbEmployee = dao.findemployeeById(id);
				if (dbEmployee != null) {
					dbEmployee.setId(id);
					dbEmployee.setEmail(email);
					structure.setMessage("Email Updated Successful");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveEmployee(dbEmployee));
//					return structure;
					return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("Employee with Id- "+id+" not Found !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbEmployee);
//					return structure;
					throw new IdNotFound(id+" Id not found !!!");
				}
			} else {
				throw new WrongEmailFormat("Wrong eamil format !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateName(int id, String name) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			if (name.length()>=2) {
				Employee dbEmployee = dao.findemployeeById(id);
				if (dbEmployee != null) {
					dbEmployee.setId(id);
					dbEmployee.setName(name);
					structure.setMessage("Name Updated Successful");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveEmployee(dbEmployee));
//					return structure;
					return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("Employee with Id- "+id+" not Found !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbEmployee);
//					return structure;
					throw new IdNotFound(id+" Id not found !!!");
				}
			} else {
				throw new NameLength("Check length of Name !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateAddress(int id, String address) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			if (address.length()>=3) {
				Employee dbEmployee = dao.findemployeeById(id);
				if (dbEmployee != null) {
					dbEmployee.setId(id);
					dbEmployee.setAddress(address);
					structure.setMessage("Address updated Successful");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveEmployee(dbEmployee));
//					return structure;
					return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("Employee with Id- "+id+" not Found !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbEmployee);
//					return structure;
					throw new IdNotFound(id+" Id not found !!!");
				}
			} else {
				throw new WrongAddressFormat("Check address !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (id>=0) {
			if (salary>0) {
				Employee dbEmployee = dao.findemployeeById(id);
				if (dbEmployee != null) {
					dbEmployee.setId(id);
					dbEmployee.setSalary(salary);
					
					double sal = salary;
					if (sal < 10000) {
						dbEmployee.setGrade('D');
					} else if (sal >= 10000 && sal < 20000) {
						dbEmployee.setGrade('C');
					} else if (sal >= 20000 && sal < 40000) {
						dbEmployee.setGrade('B');
					} else {
						dbEmployee.setGrade('A');
					}
					
					structure.setMessage("Salary Updated Successful");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveEmployee(dbEmployee));
//					return structure;
					return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("Employee with Id- "+id+" not Found !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbEmployee);
//					return structure;
					throw new IdNotFound(id+" Id not found !!!");
				}
			} else {
				throw new NoNegativeElement("Negative, zero Salary is not allowed !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not alloed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByPhone(long phone) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		int temp=0;
		for (long i = phone; i!=0; i/=10) {
			long rem = i%10;
			temp++;
		}
		if (temp==10) {
			Employee dbEmployee = dao.findByPhone(phone);
			if (dbEmployee != null) {
				structure.setMessage("Employee found");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbEmployee);
//				return structure;
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
				
			} else {
//				structure.setMessage("Employee with Phone- "+phone+" not Found !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbEmployee);
//				return structure;
				throw new EmployeeNotFound("No employee found with this "+phone);
			}
		} else {
			throw new PhoneNumberLimit("Check Phone number !!!");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (email.endsWith("@gmail.com")) {
			Employee dbEmployee = dao.findByEmail(email);
			if (dbEmployee != null) {
				structure.setMessage("Employee found");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbEmployee);
//				return structure;
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("Employee with Email- "+email+" not Found !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbEmployee);
//				return structure;
				throw new EmployeeNotFound("No employee found with this "+email);
			}
		} else {
			throw new WrongEmailFormat("Emial is not in proper format !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesByAddress(String address) {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (address.length()>=3) {
			List<Employee> empList = dao.employeesByAddress(address);
			if (empList.isEmpty()) {
//				strEmpList.setMessage("Employees not found");
//				strEmpList.setStatus(HttpStatus.NOT_FOUND.value());
//				strEmpList.setData(empList);
//				return strEmpList;
				throw new EmployeeNotFound("No employees found with this "+address);
			} else {
				structure.setMessage("Employees with Address- "+address+" not Found !!!");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(empList);
//				return strEmpList;
				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
			}
		} else {
			throw new WrongAddressFormat("Check address again !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesByName(String name) {
		ResponseStructure<List<Employee>> strEmpList = new ResponseStructure<List<Employee>>();
		if (name.length()>=2) {
			List<Employee> empList = dao.employeesByName(name);
			if (!empList.isEmpty()) {
				strEmpList.setMessage("Employees found");
				strEmpList.setStatus(HttpStatus.FOUND.value());
				strEmpList.setData(empList);
//				return strEmpList;
				return new ResponseEntity<ResponseStructure<List<Employee>>>(strEmpList, HttpStatus.FOUND);
			} else {
//				strEmpList.setMessage("Employees with Name- "+name+" not Found !!!");
//				strEmpList.setStatus(HttpStatus.NOT_FOUND.value());
//				strEmpList.setData(empList);
//				return strEmpList;
				throw new EmployeeNotFound("No employees found with Name- "+name);
			}
		} else {
			throw new NameLength("Name length is too short !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesSalaryLessThan(double salary) {
		ResponseStructure<List<Employee>> strEmpList = new ResponseStructure<List<Employee>>();
		if (salary>0) {
			List<Employee> empList = dao.employeesSalaryLessThan(salary);
			if (!empList.isEmpty()) {
				strEmpList.setMessage("Employees found");
				strEmpList.setStatus(HttpStatus.FOUND.value());
				strEmpList.setData(empList);
//				return strEmpList;
				return new ResponseEntity<ResponseStructure<List<Employee>>>(strEmpList, HttpStatus.FOUND);
			} else {
//				strEmpList.setMessage("Employees with Salary Less Than- "+salary+" not Found !!!");
//				strEmpList.setStatus(HttpStatus.NOT_FOUND.value());
//				strEmpList.setData(empList);
//				return strEmpList;
				throw new EmployeeNotFound("No employees found with Salary- "+salary);
			}
		} else {
			throw new NoNegativeElement("Negative salary not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesSalarygreaterThan(double salary) {
		ResponseStructure<List<Employee>> strEmpList = new ResponseStructure<List<Employee>>();
		if (salary > 0) {
			List<Employee> empList = dao.employeesSalarygreaterThan(salary);
			if (!empList.isEmpty()) {
				strEmpList.setMessage("Employees found");
				strEmpList.setStatus(HttpStatus.FOUND.value());
				strEmpList.setData(empList);
//				return strEmpList;
				return new ResponseEntity<ResponseStructure<List<Employee>>>(strEmpList, HttpStatus.FOUND);
			} else {
//				strEmpList.setMessage("Employees with Salary Greater Than- "+salary+" not Found !!!");
//				strEmpList.setStatus(HttpStatus.NOT_FOUND.value());
//				strEmpList.setData(empList);
//				return strEmpList;
				throw new EmployeeNotFound("No employees found with Salary- "+salary);
			}
		} else {
			throw new NoNegativeElement("Negative salary not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesSalaBetween(double salary1, double salary2) {
		ResponseStructure<List<Employee>> strEmpList = new ResponseStructure<List<Employee>>();
		if (salary1>=0 || salary2>=0 || salary1>=salary2) {
			List<Employee> empList = dao.employeesSalaBetween(salary1, salary2);
			if (!empList.isEmpty()) {
				strEmpList.setMessage("Employees found");
				strEmpList.setStatus(HttpStatus.FOUND.value());
				strEmpList.setData(empList);
//				return strEmpList;
				return new ResponseEntity<ResponseStructure<List<Employee>>>(strEmpList, HttpStatus.FOUND);
			} else {
//				strEmpList.setMessage("Employees Between "+salary1+", "+salary2+", not Found !!!");
//				strEmpList.setStatus(HttpStatus.NOT_FOUND.value());
//				strEmpList.setData(empList);
//				return strEmpList;
				throw new EmployeeNotFound("No employees found within "+salary1+", "+salary2);
			}
		} else {
			throw new NoNegativeElement("Check entered Salary !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> employeesByGrade(char grade) {
		ResponseStructure<List<Employee>> strEmpList = new ResponseStructure<List<Employee>>();
		if (grade=='A' || grade=='a' || grade=='B' || grade=='b' || grade=='C' || grade=='c' || grade=='D' || grade=='d') {
			List<Employee> empList = dao.employeesByGrade(grade);
			if (empList.isEmpty()) {
//				strEmpList.setMessage("Employees with Grade- "+grade+" not Found !!!");
//				strEmpList.setStatus(HttpStatus.NOT_FOUND.value());
//				strEmpList.setData(empList);
//				return strEmpList;
				throw new EmployeeNotFound("No employee found with Grade- "+grade);
				
			} else {
				strEmpList.setMessage("Employees found");
				strEmpList.setStatus(HttpStatus.FOUND.value());
				strEmpList.setData(empList);
//				return strEmpList;
				return new ResponseEntity<ResponseStructure<List<Employee>>>(strEmpList, HttpStatus.FOUND);
			}
		} else {
			throw new WrongGrade("check Grade !!!");
		}
	}
}
