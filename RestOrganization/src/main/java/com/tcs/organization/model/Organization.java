package com.tcs.organization.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
	@Id
	private Long id;
	private String name;
	private String address;

}
