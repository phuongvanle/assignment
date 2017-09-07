package com.dxc.assignment.repository;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
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
	List<AreaChart> areas, result;
	int a, b;
	@Before
	public void setUp() {
		
		try {
			areas = fitnesseRepository.originPageHistory("SuitProject001");
//			result = fitnesseRepository.uniqueOriginWithLatesDate(areas);
			a = fitnesseRepository.numberOfSuiteSetUp("SuitProject001");
			b = fitnesseRepository.numberOfTestCaseNeverRun("SuitProject001");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void originPageHistoryTest() {
		assertNotNull(areas);
	}
	
	@Test
	public void uniqueOriginWithLatesDate() {
		assertNotNull((fitnesseRepository.uniqueOriginWithLatesDate(areas)));
	}
	
	@Test 
	public void numberOfSuiteSetUp() {
		assertEquals(2, a);
	}
	
	@Test
	public void numberOfTestCaseNeverRun() {
		assertEquals(2, b);
	}
	
	@Test
	public void getAreaChart() throws IOException, ParseException {
		assertEquals(4, fitnesseRepository.getAreaChart("SuitProject001").size());
	}
	
	@Test
	public void getProjects() {
		assertEquals(2, fitnesseRepository.getProjects());
	}

}