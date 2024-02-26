package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
