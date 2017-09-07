package com.dxc.assignment.repository;

import java.awt.geom.Area;
import java.io.IOException;
import java.net.ConnectException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.dxc.assignment.model.AreaChart;

@Repository
@PropertySource("classpath:setting.properties")
public class FitnesseRepository {
	@Value("${spring.fitness.connect.url}")
	private String urlFitnesse;

	public String getUrlFitnesse() {
		return urlFitnesse;
	}

	public void setUrlFitnesse(String urlFitnesse) {
		this.urlFitnesse = urlFitnesse;
	}
	
	public List<String> getProjects() {
		List<String> projects = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(urlFitnesse).get();
			Elements trs = doc.select("table tr td a");
			for (Element element : trs) {
				String project = element.attr("href").substring(element.attr("href").indexOf(".")+1);
				projects.add(project);
			}
		} catch (IOException e) {
			System.out.println("No connection to fitnesse");
		}
		return projects;
	}

	public AreaChart getPieChart(String project) throws IOException, ParseException {
		List<AreaChart> areas = getAreaChart(project);
		return areas.get(areas.size()-1);
	}
	/**
	 * This method create list areachart model of prject
	 * @param project
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public List<AreaChart> getAreaChart(String project) throws IOException, ParseException {
		List<AreaChart> result = new ArrayList<>();
		List<AreaChart> origin = originPageHistory(project);
		List<AreaChart> data = uniqueOriginWithLatesDate(origin);
		ComparatorWithDate comparator = new ComparatorWithDate();
		Collections.sort(data, comparator);
		for (AreaChart areaChart : data) {
			areaChart.setDate(truncarFecha(areaChart.getDate()));
			result.add(areaChart);
		}
		Collections.sort(result, comparator);
		if (result.size() == 0 ) {
			return result;
		}
		Date beginDate = datePreviousFourWeekFrom(result.get(result.size()-1).getDate());
		List<AreaChart> resultOfFourWeek = removeOutOfFourWeek(beginDate, result);
		
		return resultOfFourWeek;
	}
	/**
	 * Create list with unique value date
	 * @param list
	 * @return
	 */
	public List<AreaChart> uniqueOriginWithLatesDate(List<AreaChart> origin) {
		List<AreaChart> result = new ArrayList<>();
		ComparatorWithDate comparator = new ComparatorWithDate();
		Collections.sort(origin, comparator);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		while (origin.size() > 1) {
			AreaChart areaFirst = origin.get(origin.size()-1);
			AreaChart areaSecond = origin.get(origin.size()-2);
			Date lateDate = areaFirst.getDate();
			Date nextDate = areaSecond.getDate();
			if (fmt.format(lateDate).equals(fmt.format(nextDate))) {
				if (origin.size() == 2) { 
					result.add(areaFirst);
					origin.remove(origin.size()-2);
				} else {
					origin.remove(origin.size()-2);
				}
				
			} else {
				result.add(areaFirst);
				if (origin.size() == 2) result.add(areaSecond);
				origin.remove(origin.size()-1);
			}
		}
		
		return result;
	}
	
	
	public List<AreaChart> originPageHistory(String project) throws IOException {
		List<AreaChart> areas = new ArrayList<>();
		int numOfSuitSetUp = numberOfSuiteSetUp(project);
		String path = urlFitnesse + "/FrontPage." + project + "?pageHistory";
		Document doc = Jsoup.connect(path).get();
		Elements trs = doc.select("table tr[id]");
		int numOfNeverRun = numberOfTestCaseNeverRun(project);
		for (Element tr : trs) {
			String id = tr.attr("id");
			String date = id.substring(id.indexOf("_")+1, id.length());
			Elements tds = tr.select("td");
			int passed = Integer.parseInt(tds.select("[class^=pass_count").text()) - numOfSuitSetUp;
			int failed = Integer.parseInt(tds.select("[class^=fail_count").text());
			AreaChart area = new AreaChart();
			area.setDate(buildDateWithMilis(date));
			area.setFailed(failed);
			area.setPassed(passed);
			area.setNeverRun(numOfNeverRun);
			areas.add(area);
		}
		return areas;
	}
	
	

	/**
	 * Get all testsuite in project 
	 * @param path example http://localhost:8083/FrontPage.SuitProject001
	 * @return
	 * @throws IOException 
	 */
	public int numberOfSuiteSetUp(String project) throws IOException {
		List<String> testSuites = new ArrayList<String>();
		String path = urlFitnesse + "/FrontPage." + project;
		Document doc = Jsoup.connect(path).get();
		Elements taga = doc.select("ul[class=toc1] a[class=static]");
		return taga.size();
	}
	
	public static Date datePreviousFourWeekFrom(Date date) {
		Calendar recentDate = Calendar.getInstance();
		recentDate.setTime(date);
		recentDate.add(Calendar.WEEK_OF_YEAR, -4);
		Date res = recentDate.getTime();
		return res;
	}

	public List<AreaChart> removeOutOfFourWeek(Date date, List<AreaChart> list) {
		int index = 0;
		ComparatorWithDate comparator = new ComparatorWithDate();
		AreaChart area = new AreaChart();
		area.setDate(date);
		if (list.contains(area)) {
			 index = list.indexOf(area);
			 for (int i = list.size(); i >= 0; i--) {
					if (i < index) {
						list.remove(i);
					}
				}
		} else {
			list.add(area);
			Collections.sort(list, comparator);
			index = list.indexOf(area);
			for (int i = list.size(); i >= 0; i--) {
				if (i <= index) {
					list.remove(i);
				}
			}
		}
		
		return list;
	}
	
	public int numberOfTestCaseNeverRun(String project) throws IOException {
		List<String> testSuits = new ArrayList<String>();
		String path = urlFitnesse + "/FrontPage." + project;
		Document doc = Jsoup.connect(path).get();
		Elements taga = doc.select("ul[class=toc1] a[class=suite]");
		int count = 0;
		for (Element element : taga) {
			String suite = element.attr("href").substring(element.attr("href").lastIndexOf(".")+1);
			Document docSuit = Jsoup.connect(path + "." + suite).get();
			Elements tagTds = docSuit.select("table td");
			for (Element td : tagTds) {
				if (td.text().equalsIgnoreCase("Never Run")) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static Date buildDate(String days){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(days);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date buildDateWithMilis(String days){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMddHHmmss").parse(days);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private Date truncarFecha(Date fechaParametro) throws ParseException {
	    String fecha="";
	    DateFormat outputFormatter = new SimpleDateFormat("yyyy/MM/dd");
	    fecha =outputFormatter.format(fechaParametro);
	    return outputFormatter.parse(fecha);
	}

}
