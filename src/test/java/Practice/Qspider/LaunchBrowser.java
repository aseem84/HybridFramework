package Practice.Qspider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.PropertiesFileUtility;
import genericUtilities.SeleniumUtility;

public class LaunchBrowser {
	//public WebDriver driver;
	
	public static WebDriver launchBrowserMethod() throws Throwable {
		WebDriver driver;
		SeleniumUtility sUtil =new SeleniumUtility();
		PropertiesFileUtility rpf = new PropertiesFileUtility();
		String browserName = rpf.readPropertiesFileGeneric("browser");
		String url = rpf.readPropertiesFileGeneric("URL");

		// Lunching if Chrome Browser and Open the Url
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.get(url);
		System.out.println("Browser Opened and Page loaded Successfully");
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);
		return driver;

	}
}
