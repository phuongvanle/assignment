package com.dxc.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.assignment.model.User;
import com.dxc.assignment.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void resetCredentials() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setPassword(passwordEncoder.encode("123"));
			userRepository.save(user);
		}
	}

	@Override
	public User findById(Long id) {
		return null;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public List<User> findAll() {
		return null;
	}
	
	

}
