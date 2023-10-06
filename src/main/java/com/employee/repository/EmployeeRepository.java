package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
