package com.example.jobportal.responsedto;

import java.util.Map;

import com.example.jobportal.enums.ProfileType;

public class SocialProfileResponse {

	private int socialProfileId;
	private ProfileType profileType;
	private String profileUrl;

	private Map<String, String> options;

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

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

}
