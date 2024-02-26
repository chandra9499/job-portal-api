package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer>{

//	@Query("select r from Resume where ")
//	List<Resume> findBySkillName(String skillName);

}
