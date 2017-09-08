package com.dxc.assignment.repository;

import java.util.Comparator;

import com.dxc.assignment.model.TestExecution;

public class ComparatorWithDate implements Comparator<TestExecution> {

	@Override
	public int compare(TestExecution o1, TestExecution o2) {
		int com = 0;
//		int year1 = Integer.parseInt(o1.getDate().substring(0, 4));
//		int month1 = Integer.parseInt(o1.getDate().substring(4, 6));
//		int day1 = Integer.parseInt(o1.getDate().substring(6, 8));
//		int year2 = Integer.parseInt(o2.getDate().substring(0, 4));
//		int month2 = Integer.parseInt(o2.getDate().substring(4, 6));
//		int day2 = Integer.parseInt(o2.getDate().substring(6, 8));

		if (o1 != null && o1.getDate() != null) {
			com = o1.getDate().compareTo(o2.getDate());
		} else if (o2 != null && o2.getDate() != null) {
			com = -1 * o2.getDate().compareTo(o1.getDate());
		}
		return com;
	}

}
