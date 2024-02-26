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
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.ResumeNotFoundByIdException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.ResumesNotFoundBySkillException;
import com.example.jobportal.exceptionhandling.UnauthorizedAccessByUserException;
import com.example.jobportal.exceptionhandling.UserNotFoundByIdException;
import com.example.jobportal.exceptionhandling.UserNotFoundByUserRole;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.ResumeRequest;
import com.example.jobportal.responsedto.ResumeResponse;
import com.example.jobportal.service.ResumeService;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SkillRepository skillRepo;

	private Resume convertResumeReqToResume(ResumeRequest resumeRequest, Resume resume) {
		resume.setObjective(resumeRequest.getObjective());
		return resume;
	}

	private ResumeResponse convertResumeToResumeResponse(Resume resume) {
		ResumeResponse resumeResponse = new ResumeResponse();
		resumeResponse.setResumeId(resume.getResumeId());
		resumeResponse.setObjective(resume.getObjective());
		Map<String,String> options=new HashMap<>();
		options.put("skills","/resumes/"+resume.getResumeId()+"/get-skills/skills");
		options.put("Projects", "/resumes/"+resume.getResumeId()+"/projects");
		options.put("Experience", "/resumes/"+resume.getResumeId()+"/experience");
		options.put("Education", "/resumes/"+resume.getResumeId()+"/education");
		resumeResponse.setOptions(options);
		return resumeResponse;

	}
	
	private List<ResumeResponse> convertToResumeResponseList(List<Resume> resumeList)
	{
		List<ResumeResponse> resumeResponseList=new ArrayList<>();

		for(Resume resume:resumeList)
		{
			Map<String,String> options=new HashMap<>();
			options.put("skills","/resumes/"+resume.getResumeId()+"/get-skills/skills");
			options.put("Projects", "/resumes/"+resume.getResumeId()+"/projects");
			options.put("Experience", "/resumes/"+resume.getResumeId()+"/experience");
			options.put("Education", "/resumes/"+resume.getResumeId()+"/education");
			
			ResumeResponse resumeResponse = convertResumeToResumeResponse(resume);
			resumeResponse.setOptions(options);
			resumeResponseList.add(resumeResponse);
		}
		return resumeResponseList;
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponse>> addResume(ResumeRequest resumeRequest, int userId) {
		Optional<User> optional = userRepo.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			if (user.getUserRole() == UserRole.APPLICANT) {
				Resume resume = convertResumeReqToResume(resumeRequest, new Resume());
				resume.setUser(user);
				Resume resume2 = resumeRepo.save(resume);

				ResumeResponse resumeResponse = convertResumeToResumeResponse(resume2);

				ResponseStructure<ResumeResponse> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setData(resumeResponse);
				responseStructure.setMessage("Resume Data Added Successfully!!");

				return new ResponseEntity<ResponseStructure<ResumeResponse>>(responseStructure, HttpStatus.OK);

			} else {
				throw new UserNotFoundByUserRole("User role is not present");
			}

		} else {
			throw new UserNotFoundByIdException("user with the given  Id not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponse>> findByResumeId(int resumeId) {

		Optional<Resume> optional = resumeRepo.findById(resumeId);

		if (optional.isPresent()) {
			ResumeResponse resumeResponse = convertResumeToResumeResponse(optional.get());

			ResponseStructure<ResumeResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("user found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(resumeResponse);

			return new ResponseEntity<ResponseStructure<ResumeResponse>>(responseStructure, HttpStatus.FOUND);

		}

		else
			throw new ResumeNotFoundByIdException("Resume with the given  Id not present");

	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponse>> updateByResumeId(ResumeRequest resumeRequest,
			int resumeId) {
		Optional<Resume> optional = resumeRepo.findById(resumeId);

		if (optional.isPresent()) {
			ResumeResponse resumeResponse = convertResumeToResumeResponse(optional.get());

			ResponseStructure<ResumeResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("user found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(resumeResponse);

			return new ResponseEntity<ResponseStructure<ResumeResponse>>(responseStructure, HttpStatus.FOUND);

		}

		else
			throw new ResumeNotFoundByIdException("Resume with the given  Id not present");
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponse>> deleteByResumeId(int resumeId) {
		Optional<Resume> optional = resumeRepo.findById(resumeId);

		if (optional.isPresent()) {
			resumeRepo.deleteById(resumeId);
			ResumeResponse resumeResponse = convertResumeToResumeResponse(optional.get());

			ResponseStructure<ResumeResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Resume deleted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setData(resumeResponse);

			return new ResponseEntity<ResponseStructure<ResumeResponse>>(responseStructure, HttpStatus.OK);

		}

		else
			throw new ResumeNotFoundByIdException("Resume with the given  Id not present");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ResumeResponse>>> findAll() {
		List<Resume> resumes = resumeRepo.findAll();
		if (!resumes.isEmpty()) {
			List<ResumeResponse> list = new ArrayList<>();
			for (Resume  resume : resumes) {
				ResumeResponse  resumeResponse=convertResumeToResumeResponse(resume);
				list.add(resumeResponse);
			}

			ResponseStructure<List<ResumeResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Resume Records Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<ResumeResponse>>>(structure, HttpStatus.FOUND);
		} else {
			throw new ResumeNotFoundException("Resume Data Not Present!!");
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<List<ResumeResponse>>> findResumeBySkill(int userId, String skillName) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));
		if(user.getUserRole().equals(UserRole.EMPLOYER))
		{
			List<Resume> skilledList=new ArrayList<>();

			List<Resume> resumeList = resumeRepo.findAll();

			for(Resume resume:resumeList)
			{
				List<Skill> skillsList = resume.getSkills();
				for(Skill skill:skillsList)
				{
					if(skill.getSkillName().equalsIgnoreCase(skillName))
					{
						skilledList.add(resume);
					}
				}
			}
			
			if(skilledList.isEmpty())
			{
				throw new ResumesNotFoundBySkillException("Resumes not found with the given skill "+skillName);
			}
			List<ResumeResponse> resumeResponseList = convertToResumeResponseList(skilledList);

			ResponseStructure<List<ResumeResponse>> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponseList);
			responseStructure.setMessage("Resumes found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<ResumeResponse>>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("Not allowed to access");
		}
	}
}

