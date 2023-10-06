package com.employee.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entities.Employee;
import com.employee.exporter.ExcelExporter;
import com.employee.exporter.PDFExporter;
import com.employee.services.EmployeeService;
import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/home")
	public String home() {
		return "Home Page";
	}
	
	//GET all employees
	@GetMapping("/")
	public List<Employee> getEmployees() {
		return this.employeeService.getEmployees();
	}
	
	//GET one employee
	@GetMapping("/{id}")
	public Optional<Employee> getEmployee(@PathVariable String id) {
		return this.employeeService.getEmployee(id);
	}
	
	//POST new employee
	@PostMapping("/")
	public Employee addEmployee(@RequestBody Employee employee) {
		return this.employeeService.addEmployee(employee);
	}
	
	//Update salary of existing employee
	@PutMapping("/updateSalary/{id}/{newSalary}")
	public Employee updateSalary(@PathVariable String id, @PathVariable  int newSalary) {
		return this.employeeService.updateSalary(id,newSalary);
	}
	
	//Update designation of existing employee
	@PutMapping("/updateDesignation/{id}/{newDesignation}")
	public Employee updateDesignation(@PathVariable String id, @PathVariable String newDesignation) {
		return this.employeeService.updateDesignation(id, newDesignation);
	}
	
	//Update address of existing employee
	@PutMapping("/updateAddress/{id}/{newAddress}")
	public Employee updateAddress(@PathVariable String id, @PathVariable String newAddress) {
		return this.employeeService.updateAddress(id, newAddress);
	}
	
	@GetMapping("/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employee_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Employee> employee = employeeService.getEmployees();
         
        PDFExporter exporter = new PDFExporter(employee);
        exporter.export(response);
	}
	
	@GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employee_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Employee> listUsers = employeeService.getEmployees();
         
        ExcelExporter excelExporter = new ExcelExporter(listUsers);
         
        excelExporter.export(response);    
    } 
}
