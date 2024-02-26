package com.example.jobportal.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.SocialProfile;
import com.example.jobportal.enums.ProfileType;
import com.example.jobportal.exceptionhandling.ResumeNotFoundByIdException;
import com.example.jobportal.exceptionhandling.SocialProfileNotFoundByIdException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SocialProfileRepository;
import com.example.jobportal.requestdto.SocialProfileRequest;
import com.example.jobportal.responsedto.SocialProfileResponse;
import com.example.jobportal.service.SocialProfileService;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class SocialProfileServiceImpl implements SocialProfileService {

	@Autowired
	private SocialProfileRepository socialProfileRepo;

	@Autowired
	private ResumeRepository resumeRepo;

	private SocialProfile convertToSocialProfile(SocialProfileRequest socialProfileRequest,
			SocialProfile socialProfile) {
		socialProfile.setProfileType(socialProfileRequest.getProfileType());
		socialProfile.setProfileUrl(socialProfileRequest.getProfileUrl());
		return socialProfile;
	}

	private SocialProfileResponse convertToSocialProfileResponse(SocialProfile socialProfile) {
		SocialProfileResponse socialProfileResponse = new SocialProfileResponse();
		socialProfileResponse.setSocialProfileId(socialProfile.getSocialProfileId());
		socialProfileResponse.setProfileType(socialProfile.getProfileType());
		socialProfileResponse.setProfileUrl(socialProfile.getProfileUrl());
		return socialProfileResponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> insert(SocialProfileRequest socialProfileRequest,
			int resumeId) {

		Optional<Resume> optional = resumeRepo.findById(resumeId);
		if (optional.isPresent()) {
			Resume resume = optional.get();

			ProfileType profileType = socialProfileRequest.getProfileType();

			// Check if the profile type already exists in the database
			boolean profileTypeExists = resume.getSocialProfiles().stream()
					.anyMatch(socialProfile -> socialProfile.getProfileType() == profileType);

			if (profileTypeExists) {
				throw new IllegalStateException(profileType + " already exists in the database.");
			}

			// Create a new socialProfile object and convert the request to socialProfile
			SocialProfile socialProfile = convertToSocialProfile(socialProfileRequest, new SocialProfile());
			socialProfile.setResume(resume);

			socialProfileRepo.save(socialProfile);

			// Add the new SocialProfile to the list of SocialProfiles in the resume
			resume.getSocialProfiles().add(socialProfile);

			// Save the updated resume back to the database
			resumeRepo.save(resume);

			SocialProfileResponse socialProfileResponse = convertToSocialProfileResponse(socialProfile);
			Map<String, String> links = new HashMap<>();
			links.put("Applicant Profile", "/resumes/" + socialProfile.getResume().getResumeId());
			socialProfileResponse.setOptions(links);

			ResponseStructure<SocialProfileResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("SocialProfile Added to Resume Successfully ");
			responseStructure.setData(socialProfileResponse);
			return new ResponseEntity<ResponseStructure<SocialProfileResponse>>(responseStructure, HttpStatus.CREATED);

		} else {
			throw new ResumeNotFoundByIdException("Resume Data Not Found By Given Id");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> findById(int socialProfileId) {
		Optional<SocialProfile> optional = socialProfileRepo.findById(socialProfileId);
		if (optional.isPresent()) {
			SocialProfileResponse socialProfileResponse = convertToSocialProfileResponse(optional.get());
			Map<String, String> links = new HashMap<>();
			links.put("Applicant Profile", "/resumes/" + optional.get().getResume().getResumeId());
			socialProfileResponse.setOptions(links);
			ResponseStructure<SocialProfileResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("SocialProfile Details Found");
			responseStructure.setData(socialProfileResponse);

			return new ResponseEntity<ResponseStructure<SocialProfileResponse>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new SocialProfileNotFoundByIdException("SocialProfile data Not found By Id");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SocialProfileResponse>>> findByResumeId(int resumeId) {
		Optional<Resume> optionalResume = resumeRepo.findById(resumeId);

		if (optionalResume.isPresent()) {

			Resume resume = optionalResume.get();

			List<SocialProfile> socialProfileList = resume.getSocialProfiles();

			if (!socialProfileList.isEmpty()) {

				ArrayList<SocialProfileResponse> responseList = new ArrayList<>();
				Map<String, String> link = new HashMap<>();
				for (SocialProfile socialProfile : socialProfileList) {
					SocialProfileResponse socialProfileResponse = convertToSocialProfileResponse(socialProfile);
					link.put("Applicant Profile", "/resumes/" + resume.getResumeId());
					socialProfileResponse.setOptions(link);
					responseList.add(socialProfileResponse);

				}

				ResponseStructure<List<SocialProfileResponse>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage(" Social Profile data Found successfully");
				responseStructure.setData(responseList);

				return new ResponseEntity<ResponseStructure<List<SocialProfileResponse>>>(responseStructure,
						HttpStatus.FOUND);
			} else
				throw new SocialProfileNotFoundByIdException(" This Applicant SocialProfile Details not presnt");
		} else
			throw new ResumeNotFoundByIdException(" Resume with given Id Not Found");
	}

	@Override
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> updateById(
			SocialProfileRequest socialProfileRequest, int socialProfileId) {
		Optional<SocialProfile> optional = socialProfileRepo.findById(socialProfileId);
		if (optional.isPresent()) {
			SocialProfile socialProfile = convertToSocialProfile(socialProfileRequest, optional.get());
			SocialProfile socialProfile2 = socialProfileRepo.save(socialProfile);
			SocialProfileResponse socialProfileResponse = convertToSocialProfileResponse(socialProfile2);

			ResponseStructure<SocialProfileResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("SocialProfile Details updated Successfully !!");
			responseStructure.setData(socialProfileResponse);

			return new ResponseEntity<ResponseStructure<SocialProfileResponse>>(responseStructure, HttpStatus.OK);

		} else {
			throw new SocialProfileNotFoundByIdException("SocialProfile data Not found By Id");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<SocialProfileResponse>> deleteById(int socialProfileId) {
		Optional<SocialProfile> optional = socialProfileRepo.findById(socialProfileId);
		if (optional.isPresent()) {
			SocialProfileResponse socialProfileResponse = convertToSocialProfileResponse(optional.get());

			socialProfileRepo.delete(optional.get());

			ResponseStructure<SocialProfileResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Social Profile data deleted Successfully");
			responseStructure.setData(socialProfileResponse);

			return new ResponseEntity<ResponseStructure<SocialProfileResponse>>(responseStructure, HttpStatus.OK);

		} else {
			throw new SocialProfileNotFoundByIdException("SocialProfile data Not found By Id");
		}

	}

}
