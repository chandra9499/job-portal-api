package com.example.jobportal.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.CompanyNotFoundByBussinessTypeException;
import com.example.jobportal.exceptionhandling.CompanyNotFoundByIdException;
import com.example.jobportal.exceptionhandling.CompanyNotFoundByLocation;
import com.example.jobportal.exceptionhandling.CompanyNotFoundByNameException;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccessException;
import com.example.jobportal.exceptionhandling.UserNotFoundByIdException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.CompanyRequest;
import com.example.jobportal.responsedto.CompanyResponse;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private UserRepository userRepo;
	

	@Autowired
	private CompanyRepository companyRepo;

	private Company convertToCompany(CompanyRequest companyRequest, Company company) {
		company.setBusinessType(companyRequest.getBusinessType());
		company.setCompanyName(companyRequest.getCompanyName());
		company.setContactEmail(companyRequest.getContactEmail());
		company.setContactPhno(companyRequest.getContactPhno());
		company.setDescription(companyRequest.getDescription());
		company.setFoundedDate(companyRequest.getFoundedDate());
		company.setWebsite(companyRequest.getWebsite());
		company.setLocation(companyRequest.getLocation());

		return company;
	}

	private CompanyResponse convertToCompResponse(Company company) {
		CompanyResponse companyResponse = new CompanyResponse();
		companyResponse.setBusinessType(company.getBusinessType());
		companyResponse.setCompanyId(company.getCompanyId());
		companyResponse.setCompanyName(company.getCompanyName());
		companyResponse.setContactEmail(company.getContactEmail());
		companyResponse.setContactPhno(company.getContactPhno());
		companyResponse.setDescription(company.getDescription());
		companyResponse.setFoundedDate(company.getFoundedDate());
		companyResponse.setWebsite(company.getWebsite());
		companyResponse.setLocation(company.getLocation());
		return companyResponse;

	}

	public ResponseEntity<ResponseStructure<CompanyResponse>> insertCompany(CompanyRequest companyRequest,
			BusinessType bussType, int userId) {
		Optional<User> optUser = userRepo.findById(userId);

		if (optUser.isPresent()) {
			User user = optUser.get();
			if (user.getUserRole() == UserRole.EMPLOYER) {

				Company company = convertToCompany(companyRequest, new Company());
				company.setBusinessType(bussType);
				company.setUser(user);
				Company company2 = companyRepo.save(company);

				CompanyResponse companyResponse = convertToCompResponse(company2);

				ResponseStructure<CompanyResponse> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Company data saved successfully");
				respStruc.setData(companyResponse);

				return new ResponseEntity<ResponseStructure<CompanyResponse>>(respStruc, HttpStatus.CREATED);
			}

			else
				throw new IllegalAccessException(" This user not Autharised to add Companies");

		} else
			throw new UserNotFoundByIdException("user with mentioned Id , not found");
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponse>> findByCompanyId(int companyId) {
		Optional<Company> optional = companyRepo.findById(companyId);
		if (!optional.isEmpty()) {
			Company company = optional.get();

			CompanyResponse companyResponse = convertToCompResponse(company);
			Map<String, String> o = new HashMap<>();
			o.put("Jobs", "/companies/" + company.getCompanyId() + "/jobs");
			companyResponse.setOptions(o);

			ResponseStructure<CompanyResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Company data is found");
			responseStructure.setData(companyResponse);

			return new ResponseEntity<ResponseStructure<CompanyResponse>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new CompanyNotFoundByIdException("Company data is not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByCompanyName(String companyName) {
		List<Company> companyList = companyRepo.findByName(companyName);
		if (!companyList.isEmpty()) {
			List<CompanyResponse> list = new ArrayList<>();
			for (Company company : companyList) {

				CompanyResponse companyResponse = convertToCompResponse(company);
				Map<String, String> o = new HashMap<>();
				o.put("Jobs", "/companies/" + company.getCompanyId() + "/jobs");
				companyResponse.setOptions(o);

				list.add(companyResponse);
			}
			ResponseStructure<List<CompanyResponse>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Company Record Found");
			responseStructure.setData(list);

			return new ResponseEntity<ResponseStructure<List<CompanyResponse>>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new CompanyNotFoundByNameException("Company data is not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByBusinessType(String businessType) {
		BusinessType businessType2 = BusinessType.valueOf(businessType);
		List<Company> companies = companyRepo.findCompanyByBusinessType(businessType2);
		if (!companies.isEmpty()) {
			List<CompanyResponse> list = new ArrayList<>();
			for (Company company : companies) {

				CompanyResponse companyResponse = convertToCompResponse(company);
				Map<String, String> o = new HashMap<>();
				o.put("Jobs", "/companies/" + company.getCompanyId() + "/jobs");
				companyResponse.setOptions(o);

				list.add(companyResponse);
			}

			ResponseStructure<List<CompanyResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Company Records Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<CompanyResponse>>>(structure, HttpStatus.FOUND);
		} else {
			throw new CompanyNotFoundByBussinessTypeException("Company data not found based on :" + businessType2);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponse>> updateByCompanyId(CompanyRequest companyRequest,
			int companyId) {
		Optional<Company> optional = companyRepo.findById(companyId);
		if (optional.isPresent()) {
			Company existingCompany = optional.get();

			Company company = convertToCompany(companyRequest, existingCompany);
			Company company2 = companyRepo.save(company);

			CompanyResponse response = convertToCompResponse(company2);

			ResponseStructure<CompanyResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Company Data Updated Successfully");
			responseStructure.setData(response);

			return new ResponseEntity<ResponseStructure<CompanyResponse>>(responseStructure, HttpStatus.ACCEPTED);

		} else {
			throw new CompanyNotFoundByIdException("Company data is not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponse>> deleteBycompanyId(int companyId) {
		Optional<Company> optional = companyRepo.findById(companyId);
		if (!optional.isEmpty()) {
			Company company = optional.get();
			companyRepo.delete(company);
			CompanyResponse companyResponse = convertToCompResponse(company);

			ResponseStructure<CompanyResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Company data is found");
			responseStructure.setData(companyResponse);

			return new ResponseEntity<ResponseStructure<CompanyResponse>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new CompanyNotFoundByIdException("Company data is not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findAll() {

		List<Company> companyList = companyRepo.findAll();
		if (!companyList.isEmpty()) {
			List<CompanyResponse> list = new ArrayList<>();
			for (Company company : companyList) {
				CompanyResponse response = convertToCompResponse(company);
				list.add(response);
			}

			ResponseStructure<List<CompanyResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Company Records Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<CompanyResponse>>>(structure, HttpStatus.FOUND);
		} else {
			throw new CompanyNotFoundException("Companies Data Not Present!!");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CompanyResponse>>> findByLocation(String companyLocation) {
		List<Company> companyList = companyRepo.findByLocation(companyLocation);
		if (!companyList.isEmpty()) {
			List<CompanyResponse> list = new ArrayList<>();
			for (Company company : companyList) {
				CompanyResponse companyResponse = convertToCompResponse(company);

				Map<String, String> o = new HashMap<>();
				o.put("Jobs", "/companies/" + company.getCompanyId() + "/jobs");
				companyResponse.setOptions(o);

				list.add(companyResponse);
			}
			ResponseStructure<List<CompanyResponse>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Company Records Found");
			responseStructure.setData(list);

			return new ResponseEntity<ResponseStructure<List<CompanyResponse>>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new CompanyNotFoundByLocation("Company data is not present based on mentioned location");
		}

	}

}
