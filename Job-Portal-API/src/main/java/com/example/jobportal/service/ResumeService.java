package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.ResumeRequest;
import com.example.jobportal.responsedto.ResumeResponse;
import com.example.jobportal.utility.ResponseStructure;

public interface ResumeService {
	public ResponseEntity<ResponseStructure<ResumeResponse>> addResume(ResumeRequest resumeRequest, int userId);

	public ResponseEntity<ResponseStructure<ResumeResponse>> findByResumeId(int resumeId);

	public ResponseEntity<ResponseStructure<ResumeResponse>> updateByResumeId(ResumeRequest resumeRequest,
			int resumeId);

	public ResponseEntity<ResponseStructure<ResumeResponse>> deleteByResumeId(int resumeId);

	public ResponseEntity<ResponseStructure<List<ResumeResponse>>> findAll();
	
	public ResponseEntity<ResponseStructure<List<ResumeResponse>>> findResumeBySkill(int userId,String skillName);

}
