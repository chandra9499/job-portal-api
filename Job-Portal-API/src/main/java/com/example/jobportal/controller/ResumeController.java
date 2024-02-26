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

import com.example.jobportal.requestdto.ResumeRequest;
import com.example.jobportal.responsedto.ResumeResponse;
import com.example.jobportal.service.ResumeService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ResumeController {
	@Autowired
	private ResumeService resumeService;

	@PostMapping("/users/{userId}/resumes")
	public ResponseEntity<ResponseStructure<ResumeResponse>> addResume(@RequestBody @Valid ResumeRequest resumeRequest,
			@PathVariable int userId) {
		return resumeService.addResume(resumeRequest, userId);
	}

	@GetMapping("/resumes/{resumeId}")
	public ResponseEntity<ResponseStructure<ResumeResponse>> findByResumeId(@PathVariable int resumeId) {
		return resumeService.findByResumeId(resumeId);
	}

	@PutMapping("/resumes/{resumeId}")
	public ResponseEntity<ResponseStructure<ResumeResponse>> updateByResumeId(
			@RequestBody @Valid ResumeRequest resumeRequest, @PathVariable int resumeId) {
		return null;
	}

	@DeleteMapping("/resumes/{resumeId}")
	public ResponseEntity<ResponseStructure<ResumeResponse>> deleteByResumeId(@PathVariable int resumeId) {
		return null;
	}

	@GetMapping("/resumes")
	public ResponseEntity<ResponseStructure<List<ResumeResponse>>> findAll() {
		return null;
	}
}
