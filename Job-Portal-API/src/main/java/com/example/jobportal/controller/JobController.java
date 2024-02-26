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

import com.example.jobportal.requestdto.JobRequest;
import com.example.jobportal.responsedto.JobResponse;
import com.example.jobportal.service.JobService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class JobController {

	@Autowired
	private JobService jobService;
	
	@PostMapping("/companies/{companyId}/jobs")
	public ResponseEntity<ResponseStructure<JobResponse>> addJob(@RequestBody @Valid JobRequest jobRequest,@PathVariable int companyId){
		return jobService.addJob(jobRequest, companyId);
	}
	
	@PutMapping("/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponse>> updateById(@RequestBody @Valid JobRequest  jobRequest,@PathVariable int jobId){
		return jobService.updateById(jobRequest, jobId);
	}
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponse>> findByJobId(@PathVariable int jobId){
		return jobService.findByJobId(jobId);
	}
	
	@GetMapping("/job-titles/{jobTitle}/jobs")
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobTitle(@PathVariable String jobTitle){
		return jobService.findByJobTitle(jobTitle);
	}
	
	
	@DeleteMapping("/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponse>> deleteByJobId(@PathVariable int jobId){
		return null;
	}
	@GetMapping("/jobs")
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findAll(){
		return jobService.findAll();
	}
	
	@GetMapping("/companies/{companyId}/jobs")
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobBasedonCompanyId(@PathVariable int companyId){
		return jobService.findByJobBasedonCompanyId(companyId);
	}
	
	@GetMapping("/locations/{companyLocation}/jobs")
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobsBasedonCompanyLocation(@PathVariable String companyLocation){
		return jobService.findByJobsBasedonCompanyLocation(companyLocation);
	}
	
}
