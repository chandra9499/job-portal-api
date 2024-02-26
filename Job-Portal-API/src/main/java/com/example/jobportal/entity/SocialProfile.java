package com.example.jobportal.entity;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.ProfileType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class SocialProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int socialProfileId;
	private ProfileType profileType;
	private String profileUrl;

	@ManyToOne
	private Resume resume;

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public int getSocialProfileId() {
		return socialProfileId;
	}

	public void setSocialProfileId(int socialProfileId) {
		this.socialProfileId = socialProfileId;
	}

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
}
