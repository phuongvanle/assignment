package com.dxc.assignment.services;

import java.util.List;

import com.dxc.assignment.model.AreaChart;

public interface AreaChartService {

	List<AreaChart> findAreaChart(String project);
	
	AreaChart findPieChart(String project);


}
