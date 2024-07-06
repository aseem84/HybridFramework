package genericUtilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class consist of generic methods related to properties file
 * 
 * @author aseem
 */
public class PropertiesFileUtility {

	private String propertiesFilePath = ".\\src\\test\\resources\\config.properties";
	public Properties prop;

	public PropertiesFileUtility() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(propertiesFilePath);
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
	public String readPropertiesFileGeneric(String key) throws Throwable {
		// return pro.get(value).toString(); // keep the return type as Object or type
		// it to String as done
		return prop.getProperty(key);
	}

	public String getBrowser() throws Throwable {
		return prop.getProperty("browser");
	}

	public String getURL() throws Throwable {
		return prop.getProperty("URL");
	}

	public String getUserName() throws Throwable {
		return prop.getProperty("uName");
	}

	public String getPassword() throws Throwable {
		return prop.getProperty("password");
	}

}
