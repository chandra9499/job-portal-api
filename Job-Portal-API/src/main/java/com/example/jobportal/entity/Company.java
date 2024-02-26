package com.example.jobportal.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.BusinessType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Company {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int  companyId ;
	private String companyName;
	private LocalDate foundedDate;
	private String description;
	private BusinessType businessType;
	private String contactEmail;
	private long contactPhno;
	private String location;
	private String website;
	
	
	@OneToMany(mappedBy = "company")
	private List<Job> jobList=new ArrayList<>();
	
	@ManyToOne
	private User user;
	
	public List<Job> getJobList() {
		return jobList;
	}
	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
	
	
}
