package com.dxc.assignment.repository;

import java.util.Comparator;

import com.dxc.assignment.model.AreaChart;

public class ComparatorWithDate implements Comparator<AreaChart> {

	@Override
	public int compare(AreaChart o1, AreaChart o2) {
		int com = 0;
		int year1 = Integer.parseInt(o1.getDate().substring(0, 4));
		int month1 = Integer.parseInt(o1.getDate().substring(4, 6));
		int day1 = Integer.parseInt(o1.getDate().substring(6, 8));
		int year2 = Integer.parseInt(o2.getDate().substring(0, 4));
		int month2 = Integer.parseInt(o2.getDate().substring(4, 6));
		int day2 = Integer.parseInt(o2.getDate().substring(6, 8));

		if (o1 != null && o1.getDate() != "") {
			com = year1 - year2;
			if (com == 0) {
				com = month1 - month2;
			}

			if (com == 0) {
				com = day1 - day2;
			}
		} else if (o2 != null && o2.getDate() != "") {
			com = -1 * (year2 - year1);
			if (com == 0) {
				com = -1 * (month2 - month1);
			}

			if (com == 0) {
				com = -1 * (day2 - day1);
			}
		}
		return com;
	}

}
