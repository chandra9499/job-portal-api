package com.example.jobportal.responsedto;

import java.time.LocalDate;
import java.util.Map;

public class JobResponse {
	private int jobId;
	private String jobTitle;
	private String jobDescription;
	private double annualSal;
	private LocalDate openingDate;
	private LocalDate closingDate;
	
	Map<String, String>options;

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public double getAnnualSal() {
		return annualSal;
	}

	public void setAnnualSal(double annualSal) {
		this.annualSal = annualSal;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

}
