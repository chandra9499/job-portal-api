package com.example.jobportal.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.UserNotFoundByIdException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.UserRequest;
import com.example.jobportal.responsedto.UserResponse;
import com.example.jobportal.service.UserService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	private User convertToUser(UserRequest userReq) {
		User user = new User();
		user.setEmail(userReq.getEmail());
		user.setPassword(userReq.getPassword());
		user.setUsername(userReq.getUsername());

		return user;
	}

	private User convertToUser(UserRequest userReq, User user) {
		user.setEmail(userReq.getEmail());
		user.setPassword(userReq.getPassword());
		user.setUsername(userReq.getUsername());

		return user;
	}

	private UserResponse convertToUserRespnse(User user) {
		UserResponse userResp = new UserResponse();
		userResp.setEmail(user.getEmail());
		userResp.setUserId(user.getUserId());
		userResp.setUsername(user.getUsername());
		userResp.setUserrole(user.getUserRole());
		return userResp;

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> insertUser(UserRequest userReq, UserRole role) {

		User user = convertToUser(userReq);
		user.setUserRole(role);
		User user2 = userRepo.save(user);

		UserResponse userResponse = convertToUserRespnse(user2);

		ResponseStructure<UserResponse> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.CREATED.value());
		respStruc.setMessage(" User data saved successfully");
		respStruc.setData(userResponse);

		return new ResponseEntity<ResponseStructure<UserResponse>>(respStruc, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {

		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			UserResponse dto = convertToUserRespnse(optUser.get());

			ResponseStructure<UserResponse> responseStruct = new ResponseStructure<UserResponse>();
			responseStruct.setMessage("user found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData(dto);

			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStruct, HttpStatus.FOUND);

		}

		else
			throw new UserNotFoundByIdException("user with the given  Id not present");

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@Valid UserRequest userReq, int userId) {

		Optional<User> optUser = userRepo.findById(userId);

		if (optUser.isPresent()) {

			User user = convertToUser(userReq, optUser.get());

			User user2 = userRepo.save(user);

			UserResponse userResponse = convertToUserRespnse(user2);

			ResponseStructure<UserResponse> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
			respStruc.setMessage(" User data updated successfully");
			respStruc.setData(userResponse);

			return new ResponseEntity<ResponseStructure<UserResponse>>(respStruc, HttpStatus.ACCEPTED);

		}

		else
			throw new UserNotFoundByIdException(" user not found");
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId) {
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			User user = optUser.get();
			userRepo.delete(user);
			UserResponse userResponse = convertToUserRespnse(user);

			ResponseStructure<UserResponse> responseStruct = new ResponseStructure<>();
			responseStruct.setMessage("user Data deleted successfully");
			responseStruct.setStatusCode(HttpStatus.OK.value());
			responseStruct.setData(userResponse);

			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStruct, HttpStatus.FOUND);
		}

		else
			throw new UserNotFoundByIdException("user with the given  Id not present");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers() {

		List<User> userList = userRepo.findAll();
		if (!userList.isEmpty()) {
			List<UserResponse> list = new ArrayList<>();
			for (User user : userList) {
				UserResponse response = convertToUserRespnse(user);
				list.add(response);
			}

			ResponseStructure<List<UserResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("User Records Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<UserResponse>>>(structure, HttpStatus.FOUND);
		} else {
			throw new UserNotFoundException("No Users Data Present!!");
		}
	}

}
