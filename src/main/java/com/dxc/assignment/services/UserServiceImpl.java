package com.dxc.assignment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.assignment.model.User;
import com.dxc.assignment.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
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
