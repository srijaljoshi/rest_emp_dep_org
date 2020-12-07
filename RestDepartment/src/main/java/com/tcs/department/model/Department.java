package com.tcs.department.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {	
	@Id
	private Long id;
	private String name;

}
