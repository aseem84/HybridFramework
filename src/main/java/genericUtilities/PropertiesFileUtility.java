package genericUtilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class consist of generic methods related to properties file
 * 
 * @author
 */
public class PropertiesFileUtility {

	private String propertiesFilePath = ".\\src\\test\\resources\\config.properties";
	private Properties prop;
	FileInputStream fis;
	public PropertiesFileUtility() {
		try {
			prop = new Properties();
			fis = new FileInputStream(propertiesFilePath);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will read data from properties file and return the value to
	 * caller
	 * 
	 * @param key
	 * @return String
	 * @throws Throwable
	 */
	public String readPropertiesFileGeneric(String key) {
		// return pro.get(value).toString(); // keep the return type as Object or type
		// it to String as done
		return prop.getProperty(key);
	}

	public String getBrowser()  {
		return prop.getProperty("browser");
	}

	public String getURL()  {
		return prop.getProperty("appURL");
	}

	public String getUserName()  {
		return prop.getProperty("uName");
	}

	public String getPassword()  {
		return prop.getProperty("password");
	}
	
	public String getHeadless() {
        return prop.getProperty("headless");
    }

}
