package com.dxc.assignment.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.assignment.model.User;
import com.dxc.assignment.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	protected final Log LOGGER = LogFactory.getLog(getClass());
	
	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println(username);
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
		
	}

}
