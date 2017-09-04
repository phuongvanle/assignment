package com.dxc.assignment.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxc.assignment.model.AreaChart;
import com.dxc.assignment.repository.FitnesseRepository;
@Repository
public class AreaChartDAOImpl implements AreaChartDAO {
	
	@Autowired
	FitnesseRepository fitnesseRepository;

	@Override
	public List<AreaChart> getAreaChart(String project) {
		List<AreaChart> areas = fitnesseRepository.getAreaChart(project);
		return areas;
	}

}
