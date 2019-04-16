package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateHandle {
	public static Date StringToDate(String dateStr) {
		Date result = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = dateFormat.parse(dateStr);
		}catch(ParseException e){
	        e.printStackTrace();
	    }
		
		return result;
	}
	
	public static ArrayList<String> getCurrentTime(){
		ArrayList<String> currentTime = new ArrayList<String>();
		
		//SET TIME IN OF TABLE
		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		currentTime.add(year);
		
		String month = Integer.toString(Calendar.getInstance().get(Calendar.MONTH));
		if(month.length() < 2) {
			month = "0" + month;
		}
		currentTime.add(month);
		
		String day = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		if(day.length() < 2) {
			day = "0" + day;
		}
		currentTime.add(day);
		
		String hour = Integer.toString(Calendar.getInstance().get(Calendar.HOUR));
		if(hour.length() < 2) {
			hour = "0" + hour;
		}
		currentTime.add(hour);
		
		String minutes = Integer.toString(Calendar.getInstance().get(Calendar.MINUTE));
		if(minutes.length() < 2) {
			minutes = "0" + minutes;
		}
		currentTime.add(minutes);
		
		String seconds = Integer.toString(Calendar.getInstance().get(Calendar.SECOND));
		if(seconds.length() < 2) {
			seconds = "0" + seconds;
		}
		currentTime.add(seconds);
		
		return currentTime;
	}
}
