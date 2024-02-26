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

import com.example.jobportal.requestdto.EducationRequest;
import com.example.jobportal.responsedto.EducationResponse;
import com.example.jobportal.service.EducationService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class EducationController {
	
	@Autowired
	EducationService educationService;
	
	@PostMapping("/resumes/{resumeId}/education")
	public ResponseEntity<ResponseStructure<EducationResponse>> insert(@RequestBody EducationRequest educationRequest,@PathVariable int resumeId){
		return educationService.insert(educationRequest, resumeId);
	}
	@GetMapping("/education/{educationId}")
	public ResponseEntity<ResponseStructure<EducationResponse>> findByEducationId(@PathVariable int educationId){
		return educationService.findByEducationId(educationId);
	}
	@GetMapping("/resumes/{resumeId}/education")
	public ResponseEntity<ResponseStructure<List<EducationResponse>>> findEducationByResumeId(@PathVariable int resumeId){
		return educationService.findEducationByResumeId(resumeId);
	}
	@PutMapping("/education/{educationId}")
	public ResponseEntity<ResponseStructure<EducationResponse>> updateByEducationId(@RequestBody EducationRequest educationRequest,@PathVariable int educationId){
		return educationService.updateByEducationId(educationRequest, educationId);
	}
	@DeleteMapping("/education/{educationId}")
	public ResponseEntity<ResponseStructure<EducationResponse>> deleteByEducationId(@PathVariable int educationId){
		return educationService.deleteByEducationId(educationId);
	}


}
