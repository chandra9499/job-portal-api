package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jobportal.entity.Education;

public interface EducationRepository  extends JpaRepository<Education, Integer>{
	
	@Query("select e from Education e where e.resume.resumeId=?1")
	public List<Education> findByResumeId(int resumeId);

}
