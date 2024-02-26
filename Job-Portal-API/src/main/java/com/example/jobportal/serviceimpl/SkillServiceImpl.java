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
import com.example.jobportal.entity.Skill;
import com.example.jobportal.exceptionhandling.ResumeNotFoundByIdException;
import com.example.jobportal.exceptionhandling.SkillNotFoundByIdException;
import com.example.jobportal.exceptionhandling.SkillNotFoundByNameException;
import com.example.jobportal.exceptionhandling.SkillsNotFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.requestdto.SkillRequest;
import com.example.jobportal.responsedto.SkillResponse;
import com.example.jobportal.service.SkillService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private ResumeRepository resumeRepo;
	
	
	private SkillResponse convertToSkillResponse(Skill skill)
	{
		SkillResponse skillResponse = new SkillResponse();
		skillResponse.setSkillId(skill.getSkillId());
		skillResponse.setSkillName(skill.getSkillName());
		
		return skillResponse;
	}
	
	private List<SkillResponse> convertToSkillResponse(List<Skill> skillsList) {
		List<SkillResponse> skillResponses=new ArrayList<>();
		for(Skill skill:skillsList)
			{
				skillResponses.add(convertToSkillResponse(skill));
			}
		return skillResponses;
	}

	private Skill checkSkill(String skill) {

		Skill oldSkill = skillRepo.findBySkillName(skill);
		if (oldSkill == null) {
			Skill newSkill = new Skill();
			newSkill.setSkillName(skill);
			skillRepo.save(newSkill);
			return newSkill;

		} else
			return oldSkill;
	}
	
	private  List<Skill> convertToSkill(SkillRequest skillRequest){
		List<Skill> skillList=new ArrayList<>();
		String[] skillArray=skillRequest.getSkills();
		for(String skill: skillArray) {
			Skill skill2=checkSkill(skill);
			skillList.add(skill2);
		}
		return skillList;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> insertSkill(SkillRequest skillRequest,int resumeId) {
		Optional<Resume> optionalResume = resumeRepo.findById(resumeId);
		if (optionalResume.isPresent()) {
			List<Skill> newSkillList = convertToSkill(skillRequest);
			List<SkillResponse> skillResponsesList=convertToSkillResponse(newSkillList);
			
			List<Skill> existingSkillList=optionalResume.get().getSkills();
			existingSkillList.addAll(newSkillList);
			resumeRepo.save(optionalResume.get());
			
				ResponseStructure<List<SkillResponse>> responseStructure = new ResponseStructure<>();
				responseStructure.setMessage("Skill Inserted successfully");
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setData(skillResponsesList);
		
			return new ResponseEntity<ResponseStructure<List<SkillResponse>>>(responseStructure,HttpStatus.ACCEPTED);
			
		} else {
			throw new ResumeNotFoundByIdException("Resume data Not Found By Id :"+resumeId);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<SkillResponse>> findById(int skillId) {
		Optional<Skill> optionSkill = skillRepo.findById(skillId);
		if (optionSkill.isPresent()) {

			SkillResponse skillResponse = convertToSkillResponse(optionSkill.get());

			ResponseStructure<SkillResponse> responseStruct = new ResponseStructure<SkillResponse>();
			responseStruct.setMessage("Skill data Found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData(skillResponse);

			return new ResponseEntity<ResponseStructure<SkillResponse>>(responseStruct, HttpStatus.FOUND);

		}

		else
			throw new SkillNotFoundByIdException("Skill with the given  Id not present");

	}

	@Override
	public ResponseEntity<ResponseStructure<SkillResponse>> FindByName(String skillName) {
		Skill skill = skillRepo.findBySkillName(skillName);
		if (skill != null) {

			SkillResponse skillResponse = convertToSkillResponse(skill);

			ResponseStructure<SkillResponse> responseStruct = new ResponseStructure<SkillResponse>();
			responseStruct.setMessage("Skill found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData(skillResponse);

			return new ResponseEntity<ResponseStructure<SkillResponse>>(responseStruct, HttpStatus.FOUND);

		}

		else
			throw new SkillNotFoundByNameException("Skill with the given  Id not present");
	}

	@Override
	public ResponseEntity<ResponseStructure<SkillResponse>> deleteById(int skillId) {
		Optional<Skill> optionSkill = skillRepo.findById(skillId);
		if (optionSkill.isPresent()) {
			Skill skill = optionSkill.get();
			skillRepo.delete(skill);
			SkillResponse skillResponse = convertToSkillResponse(skill);

			ResponseStructure<SkillResponse> responseStruct = new ResponseStructure<SkillResponse>();
			responseStruct.setMessage("user found successfully");
			responseStruct.setStatusCode(HttpStatus.OK.value());
			responseStruct.setData(skillResponse);

			return new ResponseEntity<ResponseStructure<SkillResponse>>(responseStruct, HttpStatus.OK);
		} else
			throw new SkillNotFoundByIdException("Skill with the given  Id not present");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> findAll() {
		List<Skill> skills = skillRepo.findAll();
		if (!skills.isEmpty()) {
			List<SkillResponse> list = new ArrayList<>();
			for (Skill skill : skills) {
				SkillResponse skillResponse = convertToSkillResponse(skill);
				list.add(skillResponse);
			}

			ResponseStructure<List<SkillResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Skill Records Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<SkillResponse>>>(structure, HttpStatus.FOUND);
		} else {
			throw new SkillsNotFoundException("Skill Data Not Present!!");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> getSkillsFromResume(int resumeId) {

		Resume resume = resumeRepo.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));
		
		List<Skill> skillsList = resume.getSkills();
		
		List<SkillResponse> skillResponseList = convertToSkillResponse(skillsList);
		ResponseStructure<List<SkillResponse>> responseStructure=new ResponseStructure<>();
		responseStructure.setData(skillResponseList);
		responseStructure.setMessage("Skills found for resume successfully");
		responseStructure.setStatusCode(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<List<SkillResponse>>>(responseStructure, HttpStatus.FOUND);
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> updateSkillsForResume(
			@Valid SkillRequest updatedSkillsRequest, int resumeId) {
		
		Resume resume = resumeRepo.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));
		
		
		List<Skill> updatedSkillList = convertToSkill(updatedSkillsRequest);
		List<Skill> existingSkillList=resume.getSkills();
		
		 // Remove duplicates based on skillName
	    Map<String, Skill> skillMap = new HashMap<>();
	    for (Skill existingSkill : existingSkillList) {
	        skillMap.put(existingSkill.getSkillName(), existingSkill);
	    }

	    for (Skill updatedSkill : updatedSkillList) {
	        skillMap.put(updatedSkill.getSkillName(), updatedSkill);
	    }

	    // Set the updated skill list to the resume
	    existingSkillList = new ArrayList<>(skillMap.values());
	    resume.setSkills(existingSkillList);

	    // Save the updated resume
	    resumeRepo.save(resume);
		
		List<SkillResponse> skillResponseList = convertToSkillResponse(updatedSkillList);
		
		ResponseStructure<List<SkillResponse>> responseStructure=new ResponseStructure<>();
		responseStructure.setData(skillResponseList);
		responseStructure.setMessage("Skills updated for resumeid "+ resumeId +" successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<SkillResponse>>>(responseStructure, HttpStatus.OK);
	}
}
