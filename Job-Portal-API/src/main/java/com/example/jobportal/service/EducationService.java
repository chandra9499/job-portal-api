package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.EducationRequest;
import com.example.jobportal.responsedto.EducationResponse;
import com.example.jobportal.utility.ResponseStructure;

public interface EducationService {
	
	public ResponseEntity<ResponseStructure<EducationResponse>> insert(EducationRequest educationRequest,int resumeId);
	public ResponseEntity<ResponseStructure<EducationResponse>> findByEducationId(int educationId);
	public ResponseEntity<ResponseStructure<List<EducationResponse>>> findEducationByResumeId(int resumeId);
	public ResponseEntity<ResponseStructure<EducationResponse>> updateByEducationId(EducationRequest educationRequest,int educationId);
	public ResponseEntity<ResponseStructure<EducationResponse>> deleteByEducationId(int educationId);


}
