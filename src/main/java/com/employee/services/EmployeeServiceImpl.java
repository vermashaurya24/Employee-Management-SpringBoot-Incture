package com.employee.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entities.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	List<Employee> list;
	
	public EmployeeServiceImpl() {
		list = new ArrayList<>();
		list.add(new Employee("E001", "Shaurya Raj Verma", 20000, "Kathmandu Nepal", "Trainee", "NodeJS"));
		list.add(new Employee("E002", "Satyaranjan", 20000, "Odisha", "Trainee", "Java Sprinboot"));
	}
	
	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployee(String id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateSalary(String id, int newSalary) {
		Employee e = employeeRepository.findById(id).get();
		e.setSalary(newSalary);
		return employeeRepository.save(e);
	}

	@Override
	public Employee updateDesignation(String id, String newDesignation) {
		Employee e = employeeRepository.findById(id).get();
		e.setDesignation(newDesignation);
		return employeeRepository.save(e);
	}

	@Override
	public Employee updateAddress(String id, String newAddress) {
		Employee e = employeeRepository.findById(id).get();
		e.setAddress(newAddress);
		return employeeRepository.save(e);
	}
	
	

}
