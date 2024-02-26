package com.example.jobportal.responsedto;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.UserRole;

@Component
public class UserResponse {
	private int userId;
	private String username;
	private String email;
	private UserRole userrole;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserrole() {
		return userrole;
	}

	public void setUserrole(UserRole userrole) {
		this.userrole = userrole;
	}

}
