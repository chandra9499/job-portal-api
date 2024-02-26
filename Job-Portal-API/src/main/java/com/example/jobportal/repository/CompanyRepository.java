package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jobportal.entity.Company;
import com.example.jobportal.enums.BusinessType;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	@Query("SELECT c FROM Company c WHERE c.companyName = :companyName")
	List<Company> findByName(String companyName);

	@Query("select c from Company c where c.businessType= ?1")
	List<Company> findCompanyByBusinessType(BusinessType businessType);

	List<Company> findByLocation(String location);
	
}
