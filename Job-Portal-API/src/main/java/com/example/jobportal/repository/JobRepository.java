package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jobportal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{

	List<Job> findByJobTitle(String jobTitle);
	
	@Query("select j from Job j where j.company.companyId=?1")
	List<Job> findByCompanyId(int companyId);

	@Query("select j from Job j where j.company.location=?1")
	List<Job> findByCompanyLocation(String location);

}
