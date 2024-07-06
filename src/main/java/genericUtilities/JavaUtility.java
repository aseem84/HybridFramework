package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class JavaUtility {

	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		return sdf.format(date);
		// return new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new
		// Date()).toString();
	}

	public static String randomAlphaNumeric() {
		String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(5, 8);
		return randomAlphaNumeric.toLowerCase();
	}

	public static String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(5);
		return randomNumber;
	}

	public static String systemProperties(String property) {
		// System.getProperties().list(System.out);
		return System.getProperty(property);
	}

	public static String getBrowserInfo(WebDriver sDriver, String browserProperty) {
		
		// Cast driver to RemoteWebDriver to get capabilities
		Capabilities capabilities = ((RemoteWebDriver) sDriver).getCapabilities();

		// Get browser name and version
		if (browserProperty.equals("browserName")) {
			return capabilities.getBrowserName().toUpperCase();
		} else if (browserProperty.equals("browserVersion")) {
			return capabilities.getBrowserVersion();
		}
		return null;
	}
}
