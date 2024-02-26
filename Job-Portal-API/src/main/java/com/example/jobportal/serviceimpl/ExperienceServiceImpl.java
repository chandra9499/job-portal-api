package com.example.jobportal.serviceimpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Experience;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exceptionhandling.ExperienceNotFoundByIdException;
import com.example.jobportal.exceptionhandling.ExperienceNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundByIdException;
import com.example.jobportal.repository.ExperienceRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ExperienceRequest;
import com.example.jobportal.responsedto.ExperienceResponse;
import com.example.jobportal.service.ExperienceService;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	private ExperienceRepository experienceRepo;

	@Autowired
	private ResumeRepository resumeRepo;

	private Experience convertToExperience(ExperienceRequest experienceRequest, Experience experience) {
		experience.setCompanyName(experienceRequest.getCompanyName());
		experience.setDesignation(experienceRequest.getDesignation());
		experience.setDescription(experienceRequest.getDescription());
		experience.setStartingDate(experienceRequest.getStartingDate());
		experience.setEndDate(experienceRequest.getEndDate());

		experience.setPresent(checkPresent(experienceRequest.getEndDate()));
		return experience;
	}

	private boolean checkPresent(LocalDate endDate) {
		if (endDate == null)
			return true;
		return false;
	}

	private ExperienceResponse convertToExperienceResponse(Experience experience) {
		ExperienceResponse experienceResponse = new ExperienceResponse();
		experienceResponse.setExperienceId(experience.getExperienceId());
		experienceResponse.setCompanyName(experience.getCompanyName());
		experienceResponse.setDesignation(experience.getDesignation());
		experienceResponse.setDescription(experience.getDescription());
		experienceResponse.setStartingDate(experience.getStartingDate());
		experienceResponse.setEndDate(experience.getEndDate());
		experienceResponse.setPresent(experience.isPresent());
		experienceResponse.setWorkExperience(workExperience(experience.getEndDate(), experience.getStartingDate()));

		return experienceResponse;
	}

	private String workExperience(LocalDate endDate, LocalDate startingDate) {
		if (endDate == null) {
			endDate = LocalDate.now();
		}
		/*
		 * LocalDate date1 = LocalDate.of(2023, 1, 1); LocalDate date2 =
		 * LocalDate.of(2024, 1, 1); Period period = Period.between(date1, date2); int
		 * diffInYears = period.getYears(); int diffInMonths = period.getMonths(); int
		 * diffInDays = period.getDays();
		 */

		Period period = Period.between(startingDate, endDate);
		return period.getYears() + " year " + period.getMonths() + " Months";

	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponse>> insertExperience(ExperienceRequest experienceRequest,
			int resumeId) {
		Optional<Resume> optional = resumeRepo.findById(resumeId);
		if (optional.isPresent()) {
			Resume resume = optional.get();
			Experience experience = convertToExperience(experienceRequest, new Experience());
			experience.setResume(resume);
			Experience experience2 = experienceRepo.save(experience);
			// Add the new Experience to the list of Experience in the resume
			resume.getExperienceList().add(experience2);
			// Save the updated resume back to the database
			resumeRepo.save(resume);

			ExperienceResponse experienceResponse = convertToExperienceResponse(experience);

			ResponseStructure<ExperienceResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Experience Inserted Successfully !!");
			responseStructure.setData(experienceResponse);

			return new ResponseEntity<ResponseStructure<ExperienceResponse>>(responseStructure, HttpStatus.CREATED);

		} else {
			throw new ResumeNotFoundByIdException("Resume Not found By Id");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ExperienceResponse>>> findByResumeId(int resumeId) {

		Optional<Resume> optionalResume = resumeRepo.findById(resumeId);

		if (optionalResume.isPresent()) {

			Resume resume = optionalResume.get();

			List<Experience> experienceList = resume.getExperienceList();

			if (!experienceList.isEmpty()) {

				ArrayList<ExperienceResponse> responseList = new ArrayList<>();
				Map<String, String> hashMap = new HashMap<>();
				for (Experience experience : experienceList) {

					ExperienceResponse experienceResponse = convertToExperienceResponse(experience);
					hashMap.put("Applicant Profile", "/resumes/" + resume.getResumeId());
					experienceResponse.setOptions(hashMap);
					responseList.add(experienceResponse);

				}

				ResponseStructure<List<ExperienceResponse>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Experience data Found successfully");
				responseStructure.setData(responseList);

				return new ResponseEntity<ResponseStructure<List<ExperienceResponse>>>(responseStructure,
						HttpStatus.FOUND);
			} else
				throw new ExperienceNotFoundException(" This user has no Experience to display");
		} else
			throw new ResumeNotFoundByIdException(" Resume with given Id Not Found");
	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponse>> findById(int experienceId) {
		Optional<Experience> optional = experienceRepo.findById(experienceId);

		if (optional.isPresent()) {
			Experience experience = optional.get();

			ExperienceResponse experienceResponse = convertToExperienceResponse(experience);

			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("Applicant", "/resumes/" + experience.getResume().getResumeId());
			experienceResponse.setOptions(hashMap);

			ResponseStructure<ExperienceResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage(" Experience data Found successfully");
			responseStructure.setData(experienceResponse);

			return new ResponseEntity<ResponseStructure<ExperienceResponse>>(responseStructure, HttpStatus.FOUND);

		} else
			throw new ExperienceNotFoundByIdException("Experience with given Id Not Present");

	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponse>> updateById(ExperienceRequest experienceRequest,
			int experienceId) {
		Optional<Experience> optional = experienceRepo.findById(experienceId);
		if (optional.isPresent()) {
			Experience experience = convertToExperience(experienceRequest, optional.get());
			Experience experience2 = experienceRepo.save(experience);
			ExperienceResponse experienceResponse = convertToExperienceResponse(experience2);

			ResponseStructure<ExperienceResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Experience Updated Successfully !!");
			responseStructure.setData(experienceResponse);

			return new ResponseEntity<ResponseStructure<ExperienceResponse>>(responseStructure, HttpStatus.OK);

		} else {
			throw new ExperienceNotFoundByIdException("Experience Not found By Id");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponse>> deleteById(int experienceId) {
		Optional<Experience> optional = experienceRepo.findById(experienceId);
		if (optional.isPresent()) {
			Experience experience = optional.get();
			experienceRepo.delete(experience);
			ExperienceResponse experienceResponse = convertToExperienceResponse(experience);

			ResponseStructure<ExperienceResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Experience deleted Successfully !!");
			responseStructure.setData(experienceResponse);

			return new ResponseEntity<ResponseStructure<ExperienceResponse>>(responseStructure, HttpStatus.OK);

		} else {
			throw new ExperienceNotFoundByIdException("Experience Not found By Id");
		}
	}

}
