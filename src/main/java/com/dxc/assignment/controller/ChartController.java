package com.dxc.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.assignment.model.AreaChart;
import com.dxc.assignment.model.User;
import com.dxc.assignment.repository.UserRepository;
import com.dxc.assignment.services.AreaChartService;

@RestController
@RequestMapping("/charts")
public class ChartController {
	
	@Autowired
	private AreaChartService areChartService;
	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin
	@GetMapping("/pie/{project}")
	public AreaChart getPieChart(@PathVariable("project") String project) {
		AreaChart pie = areChartService.findPieChart(project);
		return pie;
	}
	@CrossOrigin
	@GetMapping("/area/{project}")
	public List<AreaChart> getAreaChart(@PathVariable("project") String project) {
		List<AreaChart> areaCharts = areChartService.findAreaChart(project);
		return areaCharts;
	}
	@GetMapping("/user/{username}")
	public User findByUsername(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}
	
}
