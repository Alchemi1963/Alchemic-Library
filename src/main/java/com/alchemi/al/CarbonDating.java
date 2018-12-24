package com.alchemi.al;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CarbonDating {

	private String carbondate = null;
	
	public String day;
	public String month;
	public String year;
	public String hour;
	public String minute;
	
	
	public CarbonDating(String day, String month, String year, String hour, String minute) {
		try{
			carbondate = day + "-" + month + "-" + year  + "-" + hour + "-" + minute;
		} catch (Exception e) {
			e.printStackTrace();
		}
		readCarbonDate();
	}
	
	public CarbonDating(String carbondate) {
		try {
			this.carbondate = carbondate;
			readCarbonDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CarbonDating getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		
		String day = new SimpleDateFormat("dd").format(date);
		String month = new SimpleDateFormat("MM").format(date);
		String year = new SimpleDateFormat("yyyy").format(date);
		String hour = new SimpleDateFormat("hh").format(date);
		String minute = new SimpleDateFormat("mm").format(date);
		
		return new CarbonDating(day, month, year, hour, minute);
	}
	
	public String getCarbonDate() {
		return carbondate;
	}
	
	//dd-MM-yyyy-hh-mm
	public void readCarbonDate() {
		day = carbondate.substring(0, 2);
		month = carbondate.substring(3, 5);
		year = carbondate.substring(6, 10);
		hour = carbondate.substring(11, 13);
		minute = carbondate.substring(13);
	}
}
