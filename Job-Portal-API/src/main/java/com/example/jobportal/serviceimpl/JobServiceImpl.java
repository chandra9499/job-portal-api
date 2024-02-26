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
import com.example.jobportal.entity.Job;
import com.example.jobportal.exceptionhandling.CompanyNotFoundByIdException;
import com.example.jobportal.exceptionhandling.CompanyNotFoundByLocation;
import com.example.jobportal.exceptionhandling.JobNotFoundByCompanyIdException;
import com.example.jobportal.exceptionhandling.JobNotFoundByIdException;
import com.example.jobportal.exceptionhandling.JobNotFoundByTitleException;
import com.example.jobportal.exceptionhandling.JobNotFoundException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.JobRequest;
import com.example.jobportal.responsedto.JobResponse;
import com.example.jobportal.service.JobService;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepo;

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private UserRepository userRepo;

	private Job convertJobRequestTOJob(JobRequest jobRequest) {
		Job job = new Job();
		job.setJobTitle(jobRequest.getJobTitle());
		job.setJobDescription(jobRequest.getJobDescription());
		job.setOpeningDate(null);

		return job;
	}

	private Job convertToJob(JobRequest jobRequest, Job existingJob) {
		existingJob.setJobTitle(jobRequest.getJobTitle());
		existingJob.setJobDescription(jobRequest.getJobDescription());
		existingJob.setOpeningDate(null);
		return existingJob;
	}

	private JobResponse convertJobToJobResponse(Job job) {
		JobResponse jobResponse = new JobResponse();
		jobResponse.setJobId(job.getJobId());
		jobResponse.setJobTitle(job.getJobTitle());
		jobResponse.setJobDescription(job.getJobDescription());
		return jobResponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponse>> addJob(JobRequest jobRequest, int companyId) {
		Optional<Company> compOptional = companyRepo.findById(companyId);
		if (compOptional.isEmpty()) {
			Job job = convertJobRequestTOJob(jobRequest);
			JobResponse jobResponse = convertJobToJobResponse(job);

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job data is found");
			responseStructure.setData(jobResponse);

			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new CompanyNotFoundByIdException("Company not found By Id,Please add company details !!");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponse>> findByJobId(int jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if (optional.isPresent()) {
			Job job = optional.get();
			JobResponse jobResponse = convertJobToJobResponse(job);
			Map<String, String> o = new HashMap<>();
			o.put("companies", "/companies/" + job.getCompany().getCompanyId());
			jobResponse.setOptions(o);

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job data is found");
			responseStructure.setData(jobResponse);

			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new JobNotFoundByIdException("Job Id not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobTitle(String jobTitle) {
		List<Job> jobList = jobRepo.findByJobTitle(jobTitle);
		if (!jobList.isEmpty()) {
			List<JobResponse> list = new ArrayList<>();
			for (Job job : jobList) {
				JobResponse jobResponse = convertJobToJobResponse(job);

				Map<String, String> o = new HashMap<>();
				o.put("companies", "/companies/" + job.getCompany().getCompanyId());
				jobResponse.setOptions(o);

				list.add(jobResponse);
			}
			ResponseStructure<List<JobResponse>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Records Found");
			responseStructure.setData(list);

			return new ResponseEntity<ResponseStructure<List<JobResponse>>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new JobNotFoundByTitleException("Job data is not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponse>> deleteByJobId(int jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if (optional.isPresent()) {
			Job job = optional.get();
			jobRepo.delete(job);
			JobResponse jobResponse = convertJobToJobResponse(job);

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job data is found");
			responseStructure.setData(jobResponse);

			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new JobNotFoundByIdException("Job Id not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponse>> updateById(JobRequest jobRequest, int jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if (optional.isPresent()) {
			Job existingJob = optional.get();

			Job job = convertToJob(jobRequest, existingJob);
			Job job2 = jobRepo.save(job);

			JobResponse jobResponse = convertJobToJobResponse(job2);

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Job Data Updated Successfully");
			responseStructure.setData(jobResponse);

			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.ACCEPTED);

		} else {
			throw new JobNotFoundByIdException("Job data is not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findAll() {
		List<Job> jobs = jobRepo.findAll();
		if (!jobs.isEmpty()) {
			List<JobResponse> list = new ArrayList<>();
			for (Job job : jobs) {
				JobResponse jobResponse = convertJobToJobResponse(job);

				Map<String, String> o = new HashMap<>();
				o.put("companies", "/companies/" + job.getCompany().getCompanyId());
				jobResponse.setOptions(o);

				list.add(jobResponse);
			}

			ResponseStructure<List<JobResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Job Records Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<JobResponse>>>(structure, HttpStatus.FOUND);
		} else {
			throw new JobNotFoundException("Jobs Data Not Present!!");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobsBasedonCompanyLocation(
			String companyLocation) {
		List<Company> companies = companyRepo.findByLocation(companyLocation);
		if (!companies.isEmpty()) {

			List<Job> jobList = jobRepo.findByCompanyLocation(companyLocation);
			if (!jobList.isEmpty()) {
				List<JobResponse> list = new ArrayList<>();
				for (Job job : jobList) {

					JobResponse jobResponse = convertJobToJobResponse(job);

					Map<String, String> companyHyperLink = new HashMap<>();
					companyHyperLink.put("companies", "/companies/" + job.getCompany().getCompanyId());
					jobResponse.setOptions(companyHyperLink);
					list.add(jobResponse);

				}

				ResponseStructure<List<JobResponse>> structure = new ResponseStructure<>();
				structure.setStatusCode(HttpStatus.FOUND.value());
				structure.setMessage("Review Records Found");
				structure.setData(list);

				return new ResponseEntity<ResponseStructure<List<JobResponse>>>(structure, HttpStatus.FOUND);
			} else {
				throw new JobNotFoundException("No Jobs Data Present!!");
			}

		} else {
			throw new CompanyNotFoundByLocation("company Location NotFound to Fetch the Data !!");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<JobResponse>>> findByJobBasedonCompanyId(int companyId) {
		Optional<Company> optional = companyRepo.findById(companyId);
		if (optional.isPresent()) {
			List<Job> jobList = jobRepo.findByCompanyId(companyId);
			if (!jobList.isEmpty()) {
				List<JobResponse> list = new ArrayList<>();
				for (Job job : jobList) {
					JobResponse jobResponse = convertJobToJobResponse(job);

					Map<String, String> o = new HashMap<>();
					o.put("companies", "/companies/" + job.getCompany().getCompanyId());
					jobResponse.setOptions(o);

					list.add(jobResponse);
				}
				ResponseStructure<List<JobResponse>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Job Records Found");
				responseStructure.setData(list);

				return new ResponseEntity<ResponseStructure<List<JobResponse>>>(responseStructure, HttpStatus.FOUND);

			} else {
				throw new JobNotFoundByCompanyIdException("Job data is not present Based on Company Id");
			}

		} else {
			throw new CompanyNotFoundByIdException("Company data not present based on Id");

		}
	}

}
