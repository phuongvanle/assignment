package com.dxc.assignment.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projects")
public class Project {
	@Id
	private String id;
	private String name;
	private PieChart pieCharts;
	private List<AreaChart> areaCharts;
	
	
	public Project() {
		areaCharts = new ArrayList<>();
	}


	public Project(String name, PieChart pieCharts, List<AreaChart> areaCharts) {
		this.name = name;
		this.pieCharts = pieCharts;
		this.areaCharts = areaCharts;
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


	public PieChart getPieCharts() {
		return pieCharts;
	}


	public void setPieCharts(PieChart pieCharts) {
		this.pieCharts = pieCharts;
	}


	public List<AreaChart> getAreaCharts() {
		return areaCharts;
	}


	public void setAreaCharts(List<AreaChart> areaCharts) {
		this.areaCharts = areaCharts;
	}
	
	
	
	
	
	

}
