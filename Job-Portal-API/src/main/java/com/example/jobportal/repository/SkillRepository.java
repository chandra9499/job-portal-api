package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.Skill;

public interface SkillRepository  extends JpaRepository<Skill, Integer>{

	public Skill findBySkillName(String skillName);
}
