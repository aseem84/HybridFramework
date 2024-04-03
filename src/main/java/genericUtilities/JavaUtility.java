package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class JavaUtility {

	public String getCurrentDate()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String currentDateAndTime = sdf.format(date);
		return currentDateAndTime;
	}

	public static String randomAlphaNumeric()
	{
		String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(5,8);
		return randomAlphaNumeric.toLowerCase();
	}

	public static String randomNumber()
	{
		String randomNumber = RandomStringUtils.randomNumeric(5);
		return randomNumber;
	}

	public static String systemProperties(String property)
	{
		//System.getProperties().list(System.out); 
		return System.getProperty(property);
	}
}
