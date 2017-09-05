package com.dxc.assignment.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.assignment.model.AreaChart;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FitnesseRepositoryTest {
	
	@Autowired
	FitnesseRepository fitnesseRepository;
	List<AreaChart> areas;
	@Before
	public void setUp() {
		areas = new ArrayList<>();
		AreaChart area = new AreaChart();
//		area.setDate("1111");C
		area.setFailed(1);
		area.setPassed(3);
		
		AreaChart area1 = new AreaChart();
//		area1.setDate("1111");
		area1.setFailed(1);
		area1.setPassed(3);
		AreaChart area3 = new AreaChart();
//		area3.setDate("1222");
		area3.setFailed(1);
		area3.setPassed(3);
		AreaChart area4 = new AreaChart();
//		area4.setDate("1222");
		area4.setFailed(4);
		area4.setPassed(3);
		
		areas.add(area);
		areas.add(area1);
		areas.add(area3);
		areas.add(area4);
	}
	
	
	@Ignore
	public void test() {
	}
	
	@Ignore
	public void getAreaChart() {
//		assertNotNull(fitnesseRepository.getAreaChart("001"));
	}
	
	@Ignore
	public void listTestSuite() {
//		assertEquals("SuiteIntegrationTesting", fitnesseRepository.getTestSuites(fitnesseRepository.getUrlFitnesse()+"/FrontPage.SuitProject001").get(0));
	}
	
	@Ignore
	public void getRecordTestFromTable() {
	}
	
	@Test
	public void uniqueAreaChartWithDate() {
//		assertEquals(2, fitnesseRepository.uniqueAreaChartWithDate(areas).get(1).getFailed());
		assertEquals(2, fitnesseRepository.getProjects());
//		assertArrayEquals( fitnesseRepository.uniqueAreaChartWithDate(areas));
	}

}
