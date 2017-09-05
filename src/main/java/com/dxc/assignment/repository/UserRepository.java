package com.dxc.assignment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.assignment.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);

}
