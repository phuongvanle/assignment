package com.dxc.assignment.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaChartServiceTest {
	
	
	@Autowired
	AreaChartService areChartService;
	
	@Test
	public void testAreaChart() {
//		assertEquals("Date(2016,3,1)", areChartService.createAreaChart("001").get(1).getDate());
	}

}
