package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.SocialProfileRequest;
import com.example.jobportal.responsedto.SocialProfileResponse;
import com.example.jobportal.utility.ResponseStructure;

public interface SocialProfileService {

	public ResponseEntity<ResponseStructure<SocialProfileResponse>> insert(SocialProfileRequest socialProfileRequest,
			int resumeId);

	public ResponseEntity<ResponseStructure<SocialProfileResponse>> findById(int socialProfileId);

	public ResponseEntity<ResponseStructure<List<SocialProfileResponse>>> findByResumeId(int resumeId);

	public ResponseEntity<ResponseStructure<SocialProfileResponse>> updateById(SocialProfileRequest socialProfileRequest,int socialProfileId);

	public ResponseEntity<ResponseStructure<SocialProfileResponse>> deleteById(int socialProfileId);

}
