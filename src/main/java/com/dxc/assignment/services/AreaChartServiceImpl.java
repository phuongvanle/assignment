package com.dxc.assignment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.assignment.dao.AreaChartDAO;
import com.dxc.assignment.model.AreaChart;
@Service
public class AreaChartServiceImpl implements AreaChartService {
	@Autowired
	AreaChartDAO areaChartDao;

	@Override
	public List<AreaChart> createAreaChart(String project) {
		return areaChartDao.getAreaChart(project);
	}

}
