package com.tcs.department.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.department.model.Department;
import com.tcs.department.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@PostMapping
	public Department createDepartment(@RequestBody Department department) {
		return departmentRepository.save(department);
	}
	
	@GetMapping
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") long id) {
		return departmentRepository.findById(id).get();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@PatchMapping("/{id}")
	public Department updateDepartment(@PathVariable("id") long id, @RequestBody Department department) {
		Department d = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department Not Found!"));
		return departmentRepository.save(department);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> delete(@PathVariable("id") long id) {
		Department d = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department Not Found!"));
		departmentRepository.deleteById(id);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}

}
