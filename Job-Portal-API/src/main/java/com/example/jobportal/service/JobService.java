package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.JobRequest;
import com.example.jobportal.responsedto.JobResponse;
import com.example.jobportal.utility.ResponseStructure;

public interface JobService {
	
	public ResponseEntity<ResponseStructure<JobResponse>> addJob(JobRequest jobRequest,int companyId);
	public ResponseEntity<ResponseStructure<JobResponse>> updateById(JobRequest  jobRequest,int jobId);
	public ResponseEntity<ResponseStructure<JobResponse>> findByJobId(int jobId);
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobTitle(String jobTitle);
	
	public ResponseEntity<ResponseStructure<JobResponse>> deleteByJobId(int jobId);
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findAll();
	
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobBasedonCompanyId(int companyId);
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobsBasedonCompanyLocation(String companyLocation);
	

}
