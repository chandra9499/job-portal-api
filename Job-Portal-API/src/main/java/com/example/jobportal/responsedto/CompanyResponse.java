package com.example.jobportal.responsedto;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.BusinessType;

@Component
public class CompanyResponse {
	

	private int  companyId ;
	private String companyName;
	private LocalDate foundedDate;
	private String description;
	private BusinessType businessType;
	private String contactEmail;
	private long contactPhno;
	private String website;
	private String location;
	
	private Map<String, String> options;
	
	public Map<String, String> getOptions() {
		return options;
	}
	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getFoundedDate() {
		return foundedDate;
	}
	public void setFoundedDate(LocalDate foundedDate) {
		this.foundedDate = foundedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BusinessType getBusinessType() {
		return businessType;
	}
	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	public long getContactPhno() {
		return contactPhno;
	}
	public void setContactPhno(long contactPhno) {
		this.contactPhno = contactPhno;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}
