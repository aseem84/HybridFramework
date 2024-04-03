package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import objectRepository.HomePage;
import objectRepository.LoginPage;

/**
 * This class contains basic configuration annotations of TestNG
 * @author aseem
 */
public class BaseClass {
	/**
	 * This method will launch the Browser based on the browser name it read from property file
	 * @return
	 * @throws Throwable
	 */
	public SeleniumUtility sUtil =new SeleniumUtility();
	public PropertiesFileUtility pfUtility = new PropertiesFileUtility();
	public ExcelFileUtility efUtility = new ExcelFileUtility();
	public WebDriver driver;
	static WebDriver sDriver;
	
	//@BeforeSuite(groups ="SmokeTestSuite") //this one or write the below code
	//@BeforeSuite(alwaysRun = true)
	public void dbConnectOpen()
	{
		LoggerLoad.info("****** DB Connection Open Successfully ******");
	}
	
	
	@BeforeMethod(alwaysRun = true) // or @BeforeMethod(groups ="SmokeTestSuite")
	//@Parameters(value = "BrowserName") 
	//uncomment the @Parameters and pass the parameter in lauchBrowser method as String BroswerName
	public void launchBrowser() throws Throwable {
		String browserName = pfUtility.getBrowser();
		String url = pfUtility.getURL();

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
	
		sUtil.maximizeWindow(driver);
		sUtil.deleteAllCookies(driver);
		sUtil.addImplicitlyWait(driver);
		sUtil.addpageLoadTimeoutWait(driver);
		
		sDriver = driver;
		driver.get(url);
		LoggerLoad.info("=================================================================================================");
		LoggerLoad.info("****** EXECTION STARTED ******");
		LoggerLoad.info("****** Browser Opened and Page loaded Successfully ******");
	}
	
	//@BeforeMethod(groups ="SmokeTestSuite")
	//@BeforeMethod(alwaysRun = true)
	public void login() throws Throwable
	{
		String uName = pfUtility.getUserName();
		String pwd = pfUtility.getPassword();
		
		LoginPage loginPg = new LoginPage(driver);
		loginPg.loginOperation(uName, pwd);
	}	
	
	//@AfterMethod(groups ="SmokeTestSuite")
	//@AfterMethod(alwaysRun = true)
	public void logout() throws Throwable
	{
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLogOut(driver);
	}
	
	//@AfterMethod(groups ="SmokeTestSuite")
	@AfterMethod(alwaysRun = true)
	public void quitBrowser() throws InterruptedException {
		driver.quit();
		LoggerLoad.info("****** Browser tabs Closed Successfully ******");
		LoggerLoad.info("****** EXECTION COMPLETED ******");
		LoggerLoad.info("=================================================================================================");
		LoggerLoad.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	//@AfterSuite(groups ="SmokeTestSuite")
	//@AfterSuite(alwaysRun = true)
	public void dbConnectClose()
	{
		LoggerLoad.info("****** DB Connection Closed Successfully ******");
	}
	

}
