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

import com.example.jobportal.requestdto.SkillRequest;
import com.example.jobportal.responsedto.SkillResponse;
import com.example.jobportal.service.SkillService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class SkillController {

	@Autowired
	private SkillService skillService;

	@GetMapping("/skills/{skillId}")
	public ResponseEntity<ResponseStructure<SkillResponse>> findById(@PathVariable int skillId) {
		return skillService.findById(skillId);
	}

	@GetMapping("/skill-name/{skillName}/skills")
	public ResponseEntity<ResponseStructure<SkillResponse>> FindByName(@PathVariable String skillName) {
		return skillService.FindByName(skillName);
	}

	@DeleteMapping("/skills/{skillId}")
	public ResponseEntity<ResponseStructure<SkillResponse>> deleteById(@PathVariable int SkillId) {
		return skillService.deleteById(SkillId);
	}

	@GetMapping("/skills")
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> findAll() {
		return skillService.findAll();
	}

	@PostMapping("/resumes/{resumeId}/skills")
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> insertSkill(
			@RequestBody @Valid SkillRequest skillRequest, @PathVariable int resumeId) {
		return skillService.insertSkill(skillRequest, resumeId);
	}

	@GetMapping("/resumes/{resumeId}/get-skills/skills")
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> getSkillsForResume(@PathVariable int resumeId) {
		return skillService.getSkillsFromResume(resumeId);
	}

	@PutMapping("/resumes/{resumeId}/skills")
	public ResponseEntity<ResponseStructure<List<SkillResponse>>> updateSkillsForResume(
			@RequestBody @Valid SkillRequest updatedSkillsRequest, @PathVariable int resumeId) {
		return skillService.updateSkillsForResume(updatedSkillsRequest, resumeId);
	}
}
