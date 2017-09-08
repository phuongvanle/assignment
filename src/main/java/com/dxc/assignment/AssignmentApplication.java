package com.dxc.assignment;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(AssignmentApplication.class);
		SpringApplication.run(AssignmentApplication.class, args);
//		Properties properties = new Properties();
//		properties.setProperty("spring.resources.staticLocations", "classpath:/static/app");
//		app.setDefaultProperties(properties);
//		app.run(args);
	}
}
