package com.alchemi.al.objects.handling;

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
	
	/** Returns an instance of a dating system, 
	 * using seperate strings as input.
	 * 
	 * @param	day		The day (number) as a string
	 * @param	month	The month (number) as a string
	 * @param	year	The year as a string
	 * @param	hour	The hour as a string
	 * @param	minute	The minute as a string
	 * @return			CarbonDating instance
	 * */
	public CarbonDating(String day, String month, String year, String hour, String minute) {
		try{
			carbondate = day + "-" + month + "-" + year  + "-" + hour + "-" + minute;
			readCarbonDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/** Return an instance of a dating system,
	 * using a carbondate string as input.
	 * 
	 * @param carbondate	The carbondate string.
	 * @return				CarbonDating instance
	 */
	public CarbonDating(String carbondate) {
		try {
			this.carbondate = carbondate;
			readCarbonDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Get a carbondating instance using the current date and time.
	 * 
	 * @return CarbonDating instance;
	 */
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
	
	/** Get the carbondate string from the current instance.
	 * 
	 * @return The carbondate string
	 */
	public String getCarbonDate() {
		return carbondate;
	}
	
	/** Get the seperate day, month, year, hour and minute from the CarbonDating instance.
	 * 
	 */
	public void readCarbonDate() {
		day = carbondate.substring(0, 2);
		month = carbondate.substring(3, 5);
		year = carbondate.substring(6, 10);
		hour = carbondate.substring(11, 13);
		minute = carbondate.substring(13);
	}
}
