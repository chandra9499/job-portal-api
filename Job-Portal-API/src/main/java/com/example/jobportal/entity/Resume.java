package com.example.jobportal.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resumeId;
	private String objective;

	@OneToOne
	private User user;

	@ManyToMany
	private List<Skill> skills;

	@OneToMany(mappedBy = "resume")
	private List<Project> projectList;

	@OneToMany(mappedBy = "resume")
	private List<Experience> experienceList;

	@OneToMany(mappedBy = "resume")
	private List<Education> educations;
	
	@OneToMany(mappedBy = "resume")
	private List<SocialProfile>socialProfiles;

	public List<SocialProfile> getSocialProfiles() {
		return socialProfiles;
	}

	public void setSocialProfiles(List<SocialProfile> socialProfiles) {
		this.socialProfiles = socialProfiles;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<Experience> getExperienceList() {
		return experienceList;
	}

	public void setExperienceList(List<Experience> experienceList) {
		this.experienceList = experienceList;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

}
