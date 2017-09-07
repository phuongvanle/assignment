package com.dxc.assignment.services;

import java.io.IOException;
import java.net.ConnectException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.assignment.model.AreaChart;
import com.dxc.assignment.model.QProject;
import com.dxc.assignment.repository.FitnesseRepository;
import com.dxc.assignment.repository.ProjectRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
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
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return projectRepository.findByName(project).getAreaCharts();
		}
	}

	@Override
	public AreaChart findPieChart(String project) {
		AreaChart max;
		try {
			return fitnesseRepository.getPieChart(project);
		} catch (ConnectException e) {
			System.out.println("Get data from databases....");
//			QProject qProject = new QProject("project");
//			Date filterByDate =  qProject.areaCharts.any().date.max();
//			Predicate predicate = qProject.areaCharts.C
			List<AreaChart> a = this.projectRepository.findByName(project).getAreaCharts();
			return a.get(a.size()-1);
//			return projectRepository.findByName(project).getAreaCharts();
		} catch (IOException e) {
			System.out.println("Get data from databases....");
			List<AreaChart> a = this.projectRepository.findByName(project).getAreaCharts();
			return a.get(a.size()-1);
		} catch (ParseException e) {
			System.out.println("Date invalid");
			e.printStackTrace();
			List<AreaChart> a = this.projectRepository.findByName(project).getAreaCharts();
			return a.get(a.size()-1);
		}
	}

}
