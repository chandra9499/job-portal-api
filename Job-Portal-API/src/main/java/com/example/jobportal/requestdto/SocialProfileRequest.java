package com.example.jobportal.requestdto;

import com.example.jobportal.enums.ProfileType;

public class SocialProfileRequest {
	private ProfileType profileType;
	private String profileUrl;

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
