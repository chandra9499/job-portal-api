package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.requestdto.CompanyRequest;
import com.example.jobportal.responsedto.CompanyResponse;
import com.example.jobportal.utility.ResponseStructure;

public interface CompanyService {
	public ResponseEntity<ResponseStructure<CompanyResponse>> insertCompany(CompanyRequest companyRequest, BusinessType bussType,int userId);
	public ResponseEntity<ResponseStructure<CompanyResponse>> findByCompanyId(int companyId);
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByCompanyName(String companyName);
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByBusinessType(String businessType);
	public ResponseEntity<ResponseStructure<CompanyResponse>> updateByCompanyId(CompanyRequest companyRequest,int companyId);
	public ResponseEntity<ResponseStructure<CompanyResponse>> deleteBycompanyId(int companyId);
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findAll();
	
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByLocation(String companyLocation);
	
}
