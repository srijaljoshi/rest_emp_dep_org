package com.tcs.organization.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

import com.tcs.organization.model.Organization;
import com.tcs.organization.repository.OrganizationRepository;

@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@PostMapping
	public Organization createOrganization(@RequestBody(required = true)Organization organization) {
		return organizationRepository.save(organization);
	}
	@GetMapping
	public List<Organization> getOrganizations() {
		return organizationRepository.findAll();
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") long id) {
		Organization o = organizationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Could not find the organization with id " + id));

		return o;
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteOrganization(@PathVariable("id") long id) {
		organizationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Could not find the organization with id " + id));
		organizationRepository.deleteById(id);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@PatchMapping("/{id}")
	public Organization updateOrganization(@PathVariable("id") long id, @RequestBody Organization organization) {
		organizationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Could not find the organization with id " + id));

		return organizationRepository.save(organization);
	}
	
}
