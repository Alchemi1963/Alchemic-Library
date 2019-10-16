package me.alchemi.al.objects.handling;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CarbonDating {

	public String day;
	public String month;
	public String year;
	public String hour;
	public String minute;
	public String second;
	
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
	public CarbonDating(String day, String month, String year, String hour, String minute, String second) {
		
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		
	}
	
	
	/** Return an instance of a dating system,
	 * using a carbondate string as input.
	 * 
	 * @param carbondate	The carbondate string.
	 * @return				CarbonDating instance
	 */
	public CarbonDating(String carbondate) {
		try {
			readCarbonDate(carbondate);
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
		String second = new SimpleDateFormat("ss").format(date);
		
		return new CarbonDating(day, month, year, hour, minute, second);
	}
	
	/** Get the carbondate string from the current instance.
	 * 
	 * @return The carbondate string
	 */
	public String getCarbonDate() {
		return day + "-" + month + "-" + year  + "-" + hour + "-" + minute + "-" + second;
	}
	
	/** Get the seperate day, month, year, hour and minute from the CarbonDating instance.
	 * 
	 */
	public void readCarbonDate(String carbondate) {
		day = carbondate.substring(0, 2);
		month = carbondate.substring(3, 5);
		year = carbondate.substring(6, 10);
		hour = carbondate.substring(11, 13);
		minute = carbondate.substring(13, 15);
		second = carbondate.substring(15);
	}
	
	@Override
	public String toString() {
		return day + "-" + month + "-" + year  + " " + hour + ":" + minute + ":" + second;
	}
}
