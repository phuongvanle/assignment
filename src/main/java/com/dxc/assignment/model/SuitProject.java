package com.dxc.assignment.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SuitProjects")
public class SuitProject {
	@Id
	private String id;
	private String name;
	private List<TestExecution> testExecutions;
	
	
	public SuitProject() {
		testExecutions = new ArrayList<>();
	}


	public SuitProject(String name, List<TestExecution> testExecutions) {
		this.name = name;
		this.testExecutions = testExecutions;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public List<TestExecution> getAreaCharts() {
		return testExecutions;
	}


	public void setAreaCharts(List<TestExecution> testExecution) {
		this.testExecutions = testExecution;
	}
	
	
	
	
	
	

}
