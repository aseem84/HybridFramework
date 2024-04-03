package Practice.Qspider;

import org.openqa.selenium.WebDriver;

import genericUtilities.SeleniumUtility;

public class LoginVtiger {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver;
		driver = LaunchBrowser.launchBrowserMethod();
		String pageTitle = LoginApplication.login(driver);
		String exptTitle = "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM";
		if(pageTitle.equals(exptTitle))
		{
			System.out.println("Login Successful...");
		}
		else 
		{
			System.err.println("Login Fail...");
		}
		SignOut.logOut(driver);
		Thread.sleep(2000);
		SeleniumUtility.quitBrowser(driver);
	}

	

	

	

}
