package com.dxc.assignment.repository;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dxc.assignment.dao.UserDAO;
import com.dxc.assignment.model.Authority;
import com.dxc.assignment.model.User;

@Component
public class DbSeeder implements CommandLineRunner {
	
	private UserDAO userDAO;
	
	public DbSeeder(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
	public void run(String... args) throws Exception {
		User user = new User("user","$2a$04$YYoDUEgH72sR93atZv9bWuYJtui3Ino7GNGVnlRaHarGwW8ilqUqe", Arrays.asList(
					new Authority("ROLE_USER")
				));
		this.userDAO.deleteAll();
		this.userDAO.save(user);
	}

}
