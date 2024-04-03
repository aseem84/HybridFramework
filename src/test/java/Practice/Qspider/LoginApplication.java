package Practice.Qspider;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import genericUtilities.PropertiesFileUtility;
import objectRepository.LoginPage;

public class LoginApplication{
@Test
	public static String login(WebDriver driver) throws Throwable {
		
		
		PropertiesFileUtility rpf = new PropertiesFileUtility();
		String uName = rpf.readPropertiesFileGeneric("uName");
		String pwd = rpf.readPropertiesFileGeneric("password");

		// Login to vtiger Website
		LoginPage loginPg = new LoginPage(driver);
		loginPg.loginOperation(uName, pwd);
		System.out.println("Login Application Successfully");
			return driver.getTitle();
	}
}
