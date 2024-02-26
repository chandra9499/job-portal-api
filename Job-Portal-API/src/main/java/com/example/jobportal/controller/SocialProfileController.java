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

import com.example.jobportal.requestdto.SocialProfileRequest;
import com.example.jobportal.responsedto.SocialProfileResponse;
import com.example.jobportal.service.SocialProfileService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class SocialProfileController {
	
	@Autowired
	private SocialProfileService socialProfileService;
	

	@PostMapping("/resumes/{resumeId}/socialprofiles")
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> insert(@RequestBody SocialProfileRequest socialProfileRequest,
			@PathVariable int resumeId){
		return socialProfileService.insert(socialProfileRequest, resumeId);
	}
	
	@GetMapping("/socialprofiles/{socialProfileId}")
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> findById(@PathVariable int socialProfileId){
		return socialProfileService.findById(socialProfileId);
	}
	@GetMapping("/resumes/{resumeId}/socialprofiles")
	public ResponseEntity<ResponseStructure<List<SocialProfileResponse>>> findByResumeId( @PathVariable int resumeId){
		return socialProfileService.findByResumeId(resumeId);
	}
	@PutMapping("/socialprofiles/{socialProfileId}")
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> updateById(@RequestBody SocialProfileRequest socialProfileRequest,@PathVariable int socialProfileId){
		return socialProfileService.updateById(socialProfileRequest,socialProfileId);
	}
	@DeleteMapping("/socialprofiles/{socialProfileId}")
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> deleteById(@PathVariable int socialProfileId){
		return socialProfileService.deleteById(socialProfileId);
	}

}
