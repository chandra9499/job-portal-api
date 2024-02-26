package com.example.jobportal.responsedto;

import java.util.Map;

public class ResumeResponse {
	private int resumeId;
	private String objective;
	
	Map<String, String> options;
	
	public Map<String, String> getOptions() {
		return options;
	}
	public void setOptions(Map<String, String> options) {
		this.options = options;
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
}
