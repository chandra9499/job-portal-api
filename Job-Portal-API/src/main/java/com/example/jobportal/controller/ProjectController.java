package com.example.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.requestdto.ProjectRequest;
import com.example.jobportal.responsedto.ProjectResponse;
import com.example.jobportal.service.ProjectService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	

	@PostMapping("/resumes/{resumeId}/projects")
	public ResponseEntity<ResponseStructure<ProjectResponse>> insertProject(@RequestBody ProjectRequest projectRequest,@PathVariable int resumeId){
		return projectService.insertProject(projectRequest, resumeId);
	}
	@GetMapping("/resumes/{resumeId}/projects")
	public ResponseEntity<ResponseStructure<List<ProjectResponse>>> findByResumeId(@PathVariable int resumeId)
	{
		return projectService.findByResumeId(resumeId);
	}
	@GetMapping("/projects/{resumeId}")
	public ResponseEntity<ResponseStructure<ProjectResponse>> findById(@PathVariable int projectId){
		return projectService.findById(projectId);
	}
	@PutMapping("/projects/{projectId}")
	public ResponseEntity<ResponseStructure<ProjectResponse>> updateById(@RequestBody ProjectRequest projectRequest,@PathVariable int projectId){
		return projectService.updateById(projectRequest, projectId);
	}
	@DeleteMapping("/projects/{projectId}")
	public ResponseEntity<ResponseStructure<ProjectResponse>> deleteById(@PathVariable int projectId){
		return projectService.deleteById(projectId);
	}

}
