package com.dxc.assignment.services;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.assignment.dao.UserDAO;
import com.dxc.assignment.model.User;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userRepository;
	
	@Override
	public User findById(String id) {
		User u = userRepository.findOne(id);
		return u;
	}

	@Override
	public User findByUsername(String username) {
		User u = userRepository.findByUsername(username);
		return u;
	}

	@Override
	public List<User> findAll() {
		List<User> result = userRepository.findAll();
		return result;
	}

}
