package com.dxc.assignment.services;

import java.util.List;

import com.dxc.assignment.model.TestExecution;

public interface TestExecutionService {

	List<TestExecution> findAreaChart(String project);
	
	TestExecution findPieChart(String project);


}
