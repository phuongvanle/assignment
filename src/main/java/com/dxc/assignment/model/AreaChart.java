package com.dxc.assignment.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Areachart")
public class AreaChart {
	@Id
	private String id;
	private Date date;
	private int passed;
	private int failed;
	private int neverRun;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPassed() {
		return passed;
	}
	public void setPassed(int passed) {
		this.passed = passed;
	}
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
	
	public int getNeverRun() {
		return neverRun;
	}
	public void setNeverRun(int neverRun) {
		this.neverRun = neverRun;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaChart other = (AreaChart) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		
		return true;
	}
	
	
	
	
	

}
