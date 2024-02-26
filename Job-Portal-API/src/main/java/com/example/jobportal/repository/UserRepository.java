package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
