package com.dxc.assignment.services;

import java.util.List;

import com.dxc.assignment.model.User;

public interface UserService {
	
	public User findById(String id);
	public User findByUsername(String username);
	public List<User> findAll();

}
