package com.dxc.assignment.services;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.assignment.model.AreaChart;
import com.dxc.assignment.repository.FitnesseRepository;
import com.dxc.assignment.repository.ProjectRepository;
@Service
public class AreaChartServiceImpl implements AreaChartService {
	@Autowired
	FitnesseRepository fitnesseRepository;
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public List<AreaChart> findAreaChart(String project) {
		try {
			return fitnesseRepository.getAreaChart(project);
		} catch (ConnectException e) {
			System.out.println("Get data from databases....");
			return projectRepository.findByName(project).getAreaCharts();
		} catch (IOException e) {
			System.out.println("Get data from databases....");
			return projectRepository.findByName(project).getAreaCharts();
		}
	}

}
