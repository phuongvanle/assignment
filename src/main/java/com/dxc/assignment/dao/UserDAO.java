package com.dxc.assignment.dao;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.assignment.model.User;

public interface UserDAO extends MongoRepository<User, String> {
	User findByUsername(String username);
}
