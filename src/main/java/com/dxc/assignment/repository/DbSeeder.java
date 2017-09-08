package com.dxc.assignment.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dxc.assignment.model.Authority;
import com.dxc.assignment.model.SuitProject;
import com.dxc.assignment.model.User;

@Configuration
@Component
@EnableScheduling
public class DbSeeder implements CommandLineRunner {
	
	private UserRepository userRepository;
	private FitnesseRepository fitnesseRepository;
	private SuitProjectRepository projectRepository;
	
	public DbSeeder(UserRepository userRepository, FitnesseRepository fitnesseRepository, SuitProjectRepository projectRepository) {
		this.userRepository = userRepository;
		this.fitnesseRepository = fitnesseRepository;
		this.projectRepository = projectRepository;
	}
	@Override
	public void run(String... args) throws Exception {
		User user = new User("user","$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", Arrays.asList(
					new Authority("ROLE_USER")
				));
//		this.projectRepository.deleteAll();
//		for (String project : fitnesseRepository.getProjects()) {
//			SuitProject data = new SuitProject(project, fitnesseRepository.getAreaChart(project));
//			this.projectRepository.save(data);
//		}
		
		
		this.userRepository.deleteAll();
		this.userRepository.save(user);
	}
	@Scheduled(initialDelay=0, fixedRate=60000*10)
	public void autoSaveData() throws IOException, ParseException {
		for (String project : fitnesseRepository.getProjects()) {
			SuitProject data = new SuitProject(project, fitnesseRepository.getAreaChart(project));
			this.projectRepository.save(data);
		}
		System.out.println("We updated database");
	}
}
