package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.SkillRequest;
import com.example.jobportal.responsedto.SkillResponse;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface SkillService {
	
	public ResponseEntity<ResponseStructure<SkillResponse>> findById(int skillId);
	public ResponseEntity<ResponseStructure<SkillResponse>> FindByName(String skillName);
	public ResponseEntity<ResponseStructure<SkillResponse>> deleteById(int SkillId);
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> findAll();
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> insertSkill(SkillRequest skillRequest, int resumeId);
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> getSkillsFromResume(int resumeId);
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> updateSkillsForResume(
			@Valid SkillRequest updatedSkillsRequest, int resumeId);



	

}
