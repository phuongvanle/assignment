package com.dxc.assignment.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.assignment.model.PieChart;
import com.dxc.assignment.repository.FitnesseRepository;
import com.dxc.assignment.repository.ProjectRepository;
@Service
public class PieChartServiceImpl implements PieChartService {
	@Autowired
	FitnesseRepository fitnesseRepository;
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public PieChart findPieChart(String project) {
		PieChart pie;
		try {
			pie = fitnesseRepository.getPieChart(project);
		} catch (IOException e) {
			System.out.println("Get data from database...");
			pie = projectRepository.findByName(project).getPieCharts();
		}
		return pie;
	}

}
