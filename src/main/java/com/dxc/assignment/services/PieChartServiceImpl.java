package com.dxc.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.assignment.dao.PieChartDAO;
import com.dxc.assignment.model.PieChart;
@Service
public class PieChartServiceImpl implements PieChartService {
	@Autowired
	PieChartDAO pieDao;

	@Override
	public PieChart createPieChart(String project) {
		PieChart pie = pieDao.getPieChart(project);
		return pie;
	}

}
