package com.dxc.assignment.services;

import java.util.List;

import com.dxc.assignment.model.User;

public interface UserService {
	
	void resetCredentials();
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();

}
