package com.employee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_table")
public class Employee {
	@Id
	private String employee_id;
	private String name;
	private int salary;
	private String address;
	private String designation;
	private String primary_skill;
	
	public Employee(String employee_id, String name, int salary, String address, String designation,
			String primary_skill) {
		super();
		this.employee_id = employee_id;
		this.name = name;
		this.salary = salary;
		this.address = address;
		this.designation = designation;
		this.primary_skill = primary_skill;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPrimary_skill() {
		return primary_skill;
	}

	public void setPrimary_skill(String primary_skill) {
		this.primary_skill = primary_skill;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", name=" + name + ", salary=" + salary + ", address=" + address
				+ ", designation=" + designation + ", primary_skill=" + primary_skill + "]";
	}

}
