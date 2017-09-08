package com.dxc.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.assignment.model.TestExecution;
import com.dxc.assignment.model.User;
import com.dxc.assignment.repository.UserRepository;
import com.dxc.assignment.services.TestExecutionService;

@RestController
@RequestMapping("/charts")
public class ChartController {
	
	@Autowired
	private TestExecutionService testExecutionService;
	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin
	@GetMapping("/pie/{project}")
	public TestExecution getPieChart(@PathVariable("project") String project) {
		TestExecution pie = testExecutionService.findPieChart(project);
		return pie;
	}
	@CrossOrigin
	@GetMapping("/area/{project}")
	public List<TestExecution> getAreaChart(@PathVariable("project") String project) {
		List<TestExecution> areaCharts = testExecutionService.findAreaChart(project);
		return areaCharts;
	}
	@GetMapping("/user/{username}")
	public User findByUsername(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}
	
}
