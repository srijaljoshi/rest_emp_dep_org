package com.tcs.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.organization.model.Organization;

@Repository("organizationRepository")
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
//	String addOrganization(Organization organization);
//	String updateOrganization(long id, Organization organization);
//	String deleteOrganization(long id);
//	Optional<Organization> findById(long id);
//	Optional<List<Organization>> getOrganizations();
//	Optional<List<Employee>> getAllEmployeesOfOrganization(long orgId);
//	Optional<List<Department>> getAllDepartmentsOfOrganization(long orgId);

}
