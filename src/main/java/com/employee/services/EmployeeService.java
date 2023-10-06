package com.employee.services;

import java.util.List;
import java.util.Optional;


import com.employee.entities.Employee;

public interface EmployeeService {
	public List<Employee> getEmployees();
	
	public Optional<Employee> getEmployee(String id);
	
	public Employee addEmployee(Employee employee);
	
	public Employee updateSalary(String id, int newSalary);
	
	public Employee updateDesignation(String id, String newDesignation);
	
	public Employee updateAddress(String id, String newAddress);
	
	
}
