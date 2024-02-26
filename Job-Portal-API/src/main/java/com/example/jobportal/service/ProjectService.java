package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.ProjectRequest;
import com.example.jobportal.responsedto.ProjectResponse;
import com.example.jobportal.utility.ResponseStructure;

public interface ProjectService {

	public ResponseEntity<ResponseStructure<ProjectResponse>> insertProject(ProjectRequest projectRequest,int resumeId);
	public ResponseEntity<ResponseStructure<List<ProjectResponse>>> findByResumeId(int resumeId);
	public ResponseEntity<ResponseStructure<ProjectResponse>> findById(int projectId);
	public ResponseEntity<ResponseStructure<ProjectResponse>> updateById(ProjectRequest projectRequest,int projectId);
	public ResponseEntity<ResponseStructure<ProjectResponse>> deleteById(int projectId);

}
