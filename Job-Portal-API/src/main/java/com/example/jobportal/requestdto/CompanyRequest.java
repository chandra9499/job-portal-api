package com.example.jobportal.requestdto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.BusinessType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Component
public class CompanyRequest {
	
	@NotBlank(message = "User cannot be blank")
	@NotNull(message = "Customer cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String companyName;
	
	private LocalDate foundedDate;
	private String location;
	private String description;
	private BusinessType businessType;
	
	@NotBlank(message = "Customer cannot be blank")
	@NotNull(message = "Customer cannot be null")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[g][m][a][i][l]+.[c][o][m]", message = "invalid email--Should be in the extension of '@gmail.com' ")
	private String contactEmail;
	
	
	private long contactPhno;
	
	private String website;

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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getContactPhno() {
		return contactPhno;
	}
	public void setContactPhno(long contactPhno) {
		this.contactPhno = contactPhno;
	}
	
	
	
	
	

}
