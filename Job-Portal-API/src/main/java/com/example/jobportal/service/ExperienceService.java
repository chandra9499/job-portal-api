package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.ExperienceRequest;
import com.example.jobportal.responsedto.ExperienceResponse;
import com.example.jobportal.utility.ResponseStructure;

public interface ExperienceService {
	
	public ResponseEntity<ResponseStructure<ExperienceResponse>> insertExperience(ExperienceRequest experienceRequest,int resumeId);
	public ResponseEntity<ResponseStructure<List<ExperienceResponse>>> findByResumeId(int resumeId);
	public ResponseEntity<ResponseStructure<ExperienceResponse>> findById(int experienceId);
	public ResponseEntity<ResponseStructure<ExperienceResponse>> updateById(ExperienceRequest experienceRequest,int experienceId);
	public ResponseEntity<ResponseStructure<ExperienceResponse>> deleteById(int experienceId);


}
