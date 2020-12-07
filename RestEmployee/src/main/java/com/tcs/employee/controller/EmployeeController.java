package com.tcs.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.employee.exception.ResourceNotFoundException;
import com.tcs.employee.model.Employee;
import com.tcs.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable("id") long id) throws ResourceNotFoundException {
		Employee employeeFind = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found!"));

		return ResponseEntity.ok().body(employeeFind);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody Employee employee) throws ResourceNotFoundException {
		Employee employeeFind = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found!"));
		Employee employeeSave = employeeRepository.save(employee);
		return ResponseEntity.ok(employeeSave);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> delete(@PathVariable("id") long id) throws ResourceNotFoundException {
		Employee employeeFind = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found!"));

		employeeRepository.deleteById(id);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
		
	}
}
