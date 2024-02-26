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

import com.example.jobportal.requestdto.ExperienceRequest;
import com.example.jobportal.responsedto.ExperienceResponse;
import com.example.jobportal.service.ExperienceService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;
	
	@PostMapping("/resumes/{resumeId}/experience")
	public ResponseEntity<ResponseStructure<ExperienceResponse>> insertExperience(@RequestBody ExperienceRequest experienceRequest,@PathVariable int resumeId){
		return experienceService.insertExperience(experienceRequest, resumeId);
	}
	@GetMapping("/resumes/{resumeId}/experience")
	public ResponseEntity<ResponseStructure<List<ExperienceResponse>>> findByResumeId(@PathVariable int resumeId){
		return experienceService.findByResumeId(resumeId);
	}
	@GetMapping("/exprience/{experienceId}")
	public ResponseEntity<ResponseStructure<ExperienceResponse>> findById(@PathVariable int experienceId){
		return experienceService.findById(experienceId);
	}
	@PutMapping("/exprience/{experienceId}")
	public ResponseEntity<ResponseStructure<ExperienceResponse>> updateById(@RequestBody ExperienceRequest experienceRequest,@PathVariable int experienceId){
		return experienceService.updateById(experienceRequest, experienceId);
	}
	@DeleteMapping("/exprience/{experienceId}")
	public ResponseEntity<ResponseStructure<ExperienceResponse>> deleteById(@PathVariable int experienceId){
		return experienceService.deleteById(experienceId);
	}

}
