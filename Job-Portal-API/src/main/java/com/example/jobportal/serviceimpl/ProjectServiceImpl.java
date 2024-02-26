package com.example.jobportal.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Project;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exceptionhandling.ProjectNotFoundByIdException;
import com.example.jobportal.exceptionhandling.ProjectNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundByIdException;
import com.example.jobportal.repository.ProjectRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ProjectRequest;
import com.example.jobportal.responsedto.ProjectResponse;
import com.example.jobportal.service.ProjectService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	ResumeRepository resumeRepo;

	private Project convertToProject(ProjectRequest projectRequest, Project project) {

		project.setProjectName(projectRequest.getProjectName());
		project.setTechStack(convertToSet(projectRequest.getTechStack()));
		project.setDescription(projectRequest.getDescription());
		project.setWebsite(projectRequest.getWebsite());
		project.setSourceCode(projectRequest.getSourceCode());
		return project;
	}

	private Set<String> convertToSet(Set<String> techStack) {
		Set<String> techSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		for (String tech : techStack) {
			techSet.add(tech);
		}
		return techSet;
	}

	private ProjectResponse convertToProjectResponse(Project project) {
		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setProjectName(project.getProjectName());
		projectResponse.setTechStack(project.getTechStack());
		projectResponse.setDescription(project.getDescription());
		projectResponse.setWebsite(project.getWebsite());
		projectResponse.setSourceCode(project.getSourceCode());
		return projectResponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<ProjectResponse>> insertProject(@Valid ProjectRequest projectRequest,
			int resumeId) {

		Resume resume = resumeRepo.findById(resumeId)
				.orElseThrow(() -> new ResumeNotFoundByIdException("Resume not found with id " + resumeId));

		Project project = convertToProject(projectRequest, new Project());

		project.setResume(resume);
		projectRepo.save(project);

		List<Project> projectList = new ArrayList<>(); // creating a project list and adding the current project into it
		projectList.add(project);

		resume.setProjectList(projectList); // setting projectList and persisting the resume object
		resumeRepo.save(resume);

		ProjectResponse projectResponse = convertToProjectResponse(project);

		ResponseStructure<ProjectResponse> responseStructure = new ResponseStructure<>();
		responseStructure.setData(projectResponse);
		responseStructure.setMessage("Project inserted successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ProjectResponse>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ProjectResponse>>> findByResumeId(int resumeId) {

		Optional<Resume> optionalResume = resumeRepo.findById(resumeId);

		if (optionalResume.isPresent()) {

			Resume resume = optionalResume.get();

			List<Project> projectList = resume.getProjectList();

			if (!projectList.isEmpty()) {

				ArrayList<ProjectResponse> responseList = new ArrayList<ProjectResponse>();
				HashMap<String, String> hashMap = new HashMap<>();
				for (Project project : projectList) {

					ProjectResponse projectResponse = convertToProjectResponse(project);
					hashMap.put("Developer Profile", "/resumes/" + resume.getResumeId());
					projectResponse.setOptions(hashMap);
					responseList.add(projectResponse);

				}

				ResponseStructure<List<ProjectResponse>> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Project data Found successfully");
				respStruc.setData(responseList);

				return new ResponseEntity<ResponseStructure<List<ProjectResponse>>>(respStruc, HttpStatus.CREATED);
			} else
				throw new ProjectNotFoundException(" this user has no projects to display");

		} else
			throw new ResumeNotFoundByIdException(" Resume with given Id Not Found");

	}

	@Override
	public ResponseEntity<ResponseStructure<ProjectResponse>> findById(int projectId) {
		Optional<Project> optPro = projectRepo.findById(projectId);

		if (optPro.isPresent()) {
			Project project = optPro.get();

			ProjectResponse projectResponse = convertToProjectResponse(project);

			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("Applicant", "/resumes/" + project.getResume().getResumeId());
			projectResponse.setOptions(hashMap);
			ResponseStructure<ProjectResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage(" Project data Found successfully");
			responseStructure.setData(projectResponse);

			return new ResponseEntity<ResponseStructure<ProjectResponse>>(responseStructure, HttpStatus.FOUND);

		} else
			throw new ProjectNotFoundByIdException("Project with given Id Not Found");

	}

	@Override
	public ResponseEntity<ResponseStructure<ProjectResponse>> updateById(ProjectRequest projectRequest, int projectId) {
		Optional<Project> optpro = projectRepo.findById(projectId);

		if (optpro.isPresent()) {

			Project oldProject = optpro.get();

			Project Project1 = convertToProject(projectRequest, oldProject);

			Project project = projectRepo.save(Project1);
			ProjectResponse projectResponse = convertToProjectResponse(project);

			ResponseStructure<ProjectResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(" Project data updated successfully");
			responseStructure.setData(projectResponse);

			return new ResponseEntity<ResponseStructure<ProjectResponse>>(responseStructure, HttpStatus.OK);

		} else
			throw new ProjectNotFoundByIdException(" project  with given id not present");

	}

	@Override
	public ResponseEntity<ResponseStructure<ProjectResponse>> deleteById(int projectId) {
		Optional<Project> optionProject = projectRepo.findById(projectId);
		if (optionProject.isPresent()) {
			Project project = optionProject.get();
			projectRepo.delete(project);
			ProjectResponse projectResponse = convertToProjectResponse(project);

			ResponseStructure<ProjectResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Project Deleted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setData(projectResponse);

			return new ResponseEntity<ResponseStructure<ProjectResponse>>(responseStructure, HttpStatus.OK);
		} else
			throw new ProjectNotFoundByIdException("project Not Found By Id");
	}

}
