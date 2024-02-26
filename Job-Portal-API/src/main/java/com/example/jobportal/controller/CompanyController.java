package com.example.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.requestdto.CompanyRequest;
import com.example.jobportal.responsedto.CompanyResponse;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.utility.ResponseStructure;
import jakarta.validation.Valid;

@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@PostMapping("/users/{userId}/BusinessType/{buss}/companies")
	public ResponseEntity<ResponseStructure<CompanyResponse>> insertCompany(@RequestBody @Valid CompanyRequest userReq,
			@PathVariable int userId, @PathVariable BusinessType buss) {

		return companyService.insertCompany(userReq, buss, userId);
	}

	@GetMapping("/companies/{companyId}")
	public ResponseEntity<ResponseStructure<CompanyResponse>> findByCompanyId(@PathVariable int companyId) {
		return companyService.findByCompanyId(companyId);
	}

	@GetMapping("/companyNames/{companyName}/companies")
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByCompanyName(
			@PathVariable String companyName) {
		return companyService.findByCompanyName(companyName);
	}

	@GetMapping("/businessType/{businessType}/companies")
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByBusinessType(
			@PathVariable String businessType) {
		return companyService.findByBusinessType(businessType);
	}

	@PutMapping("/companies/{companyId}")
	public ResponseEntity<ResponseStructure<CompanyResponse>> updateByCompanyId(
			@RequestBody @Valid CompanyRequest companyRequest, @PathVariable int companyId) {
		return companyService.updateByCompanyId(companyRequest, companyId);
	}

	@DeleteMapping("/companies/{companyId}")
	public ResponseEntity<ResponseStructure<CompanyResponse>> deleteBycompanyId(@PathVariable int companyId) {
		return companyService.deleteBycompanyId(companyId);
	}

	@GetMapping("/companies")
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findAll() {
		return companyService.findAll();
	}
	
	@GetMapping("/location/{location}/companies")
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByLocation(@PathVariable String companyLocation){
		return companyService.findByLocation(companyLocation);
	}

}
