package com.dxc.assignment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxc.assignment.model.PieChart;
import com.dxc.assignment.repository.FitnesseRepository;
@Repository
public class PieChartDAOImpl implements PieChartDAO {
	
	@Autowired
	FitnesseRepository fitnesseRepository;
	
	@Override
	public PieChart getPieChart(String project) {
		return fitnesseRepository.getPieChart(project);
	}

}
