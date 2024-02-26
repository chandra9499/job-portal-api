package com.example.jobportal.exceptionhandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.jobportal.utility.ErrorStructure;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> allErrors = ex.getAllErrors(); // UPCASTING : ObjectError is the SuperClass of
															// FieldError
		// which extends Error Class.

		Map<String, String> mapErrors = new HashMap<String, String>();

		for (ObjectError error : allErrors) {
			FieldError fieldError = (FieldError) error; // DOWNCASTING : ObjectError -> FieldError
			String message = fieldError.getDefaultMessage(); /** VALUE **/
			String field = fieldError.getField(); /** KEY **/

			mapErrors.put(field, message);
		}
		return new ResponseEntity<Object>(mapErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> UserNotFound(UserNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" USERS NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> UserNotFoundById(UserNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" USER WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserNotFoundByUserRole.class)
	public ResponseEntity<ErrorStructure<String>> UserNotFoundById(UserNotFoundByUserRole exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" USER WITH GIVEN Role NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> CompanyNotFound(CompanyNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" COMPANIES NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CompanyNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> CompanyNotFoundById(CompanyNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" COMPANY WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CompanyNotFoundByLocation.class)
	public ResponseEntity<ErrorStructure<String>> CompanyNotFoundByLocation(CompanyNotFoundByLocation exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" COMPANY WITH GIVEN Location NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CompanyNotFoundByNameException.class)
	public ResponseEntity<ErrorStructure<String>> CompanyNotFoundByName(CompanyNotFoundByNameException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" COMPANY WITH GIVEN NAME NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CompanyNotFoundByBussinessTypeException.class)
	public ResponseEntity<ErrorStructure<String>> CompanyNotFoundBybussinessType(
			CompanyNotFoundByBussinessTypeException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" COMPANY WITH GIVEN BussinessType NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<ErrorStructure<String>> illegalAccessExcp(IllegalAccessException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" USER NOT AUTHORISED FOR THIS OPERATION");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ExperienceNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> ExperienceNotFoundById(ExperienceNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" Experience With Given Id Not Present");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ExperienceNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> ExperienceNotFound(ExperienceNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" Experience data Not Present");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(JobNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> jobNotFoundById(JobNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" JOB WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(JobNotFoundByCompanyIdException.class)
	public ResponseEntity<ErrorStructure<String>> jobNotFoundByCompanyId(JobNotFoundByCompanyIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" JOB WITH GIVEN COMPANY ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(JobNotFoundByTitleException.class)
	public ResponseEntity<ErrorStructure<String>> jobNotFoundByTitleId(JobNotFoundByTitleException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" JOB WITH GIVEN TITLE  NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> jobNotFoundException(JobNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata(" JOBS DATA ARE NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProjectNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> ProjectNotFoundById(ProjectNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("PROJECT WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> ProjectNotFound(ProjectNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("PROJECTS DATA NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResumeNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> ResumeNotFoundById(ResumeNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("RESUME WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResumeNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> ResumeNotFound(ResumeNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("RESUMES NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResumesNotFoundBySkillException.class)
	public ResponseEntity<ErrorStructure<String>> ResumesNotFoundBySkill(ResumesNotFoundBySkillException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("RESUME WITH GIVEN SKILL NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SkillNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> SkillNotFoundById(SkillNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("SKILL WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SkillsNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> SkillNotFound(SkillsNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("SKILLs NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SkillNotFoundByNameException.class)
	public ResponseEntity<ErrorStructure<String>> SkillNotFoundByName(SkillNotFoundByNameException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("SKILL WITH GIVEN Name NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedAccessByUserException.class)
	public ResponseEntity<ErrorStructure<String>> UnauthorizedAccessByUser(UnauthorizedAccessByUserException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("Unauthorized Access By User");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EducationNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> EducationNotFoundById(EducationNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("Education Details WITH GIVEN ID NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EducationListNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> EducationListNotFound(EducationListNotFoundException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("Education Details NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SocialProfileNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> SocialProfileNotFoundById(SocialProfileNotFoundByIdException exp) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setMessage(exp.getMessage()); // message what we throw in service
		es.setErrordata("SocialProfiles Details With Given Id NOT PRESENT ");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

}
