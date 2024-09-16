package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import objectRepository.HomePage;
import objectRepository.LoginPage;

/**
 * This class contains basic configuration annotations of TestNG
 */
public class BaseClass {

	/**
	 * This method will launch the Browser based on the browser name it read from property file
	 */
	public SeleniumUtility sUtil = new SeleniumUtility();
	public PropertiesFileUtility pfUtility = new PropertiesFileUtility();
	public ExcelFileUtility efUtility = new ExcelFileUtility();
	public WebDriver driver;
	static WebDriver sDriver;

	// @BeforeSuite(groups ="SmokeTestSuite") //this one or write the below code
	// @BeforeSuite(alwaysRun = true)
	public void dbConnectOpen() {
		LoggerLoad.info("****** DB Connection Open Successfully ******");
	}

	@BeforeMethod(alwaysRun = true) // or @BeforeMethod(groups ="SmokeTestSuite")
	// @Parameters(value = "BrowserName")
	// uncomment the @Parameters and pass the parameter in lauchBrowser method as
	// String String browserName
	public void launchBrowser() throws Throwable {
		String browserName = pfUtility.getBrowser();
		String url = pfUtility.getURL();
		String headless = pfUtility.getHeadless();
		String headMode = "--headless=new";

		if (headless.equalsIgnoreCase("true")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments(headMode);
				driver = new ChromeDriver(options);
			} else if (browserName.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments(headMode);
				driver = new FirefoxDriver(options);
			} else if (browserName.equalsIgnoreCase("edge")) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments(headMode);
				driver = new EdgeDriver(options);
			} else {
				driver = new ChromeDriver();
			}
			sUtil.deleteAllCookies(driver);
			sUtil.addImplicitlyWait(driver);
			sUtil.addpageLoadTimeoutWait(driver);

		} else {
			switch (browserName.toLowerCase()) {
			case "chrome":
				driver = new CustomChromeDriver();
				break;
			case "firefox":
				driver = new CustomFirefoxDriver();
				break;
			case "edge":
				driver = new CustomEdgeDriver();
				break;
			default:
				System.out.println("Unknown browser Name: " + browserName);
				System.out.println("Executing in headless mode for Chrome!");
				ChromeOptions options = new ChromeOptions();
				options.addArguments(headMode);
				driver = new ChromeDriver(options);
			}
			/*
			 * if (browserName.equalsIgnoreCase("chrome")) { } else if
			 * (browserName.equalsIgnoreCase("firefox")) { driver = new
			 * CustomFirefoxDriver(); } else if (browserName.equalsIgnoreCase("edge")) {
			 * driver = new CustomEdgeDriver(); } else {
			 * System.out.println("Unknown browser Name: " + browserName);
			 * System.out.println("Executing in headless mode for Chrome!"); ChromeOptions
			 * options = new ChromeOptions(); options.addArguments("--headless=new"); driver
			 * = new ChromeDriver(options); }
			 */
			sUtil.deleteAllCookies(driver);
			sUtil.addImplicitlyWait(driver);
			sUtil.addpageLoadTimeoutWait(driver);
		}
		driver.get(url);
		sDriver = driver;
		LoggerLoad.info(
				"=================================================================================================");
		LoggerLoad.info("****** SUITE EXECUTION STARTED ******");
		LoggerLoad.info("****** Browser Opened and Page loaded Successfully ******");
	}

	// @BeforeMethod(groups ="SmokeTestSuite")
	// @BeforeMethod(alwaysRun = true)
	public void doLogin() throws Throwable {
		String uName = pfUtility.getUserName();
		String pwd = pfUtility.getPassword();

		LoginPage loginPg = new LoginPage(driver);
		loginPg.loginOperation(uName, pwd);
	}

	// @AfterMethod(groups ="SmokeTestSuite")
	// @AfterMethod(alwaysRun = true)
	public void doLogout() throws Throwable {
		Thread.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLogOut(driver);
	}

	// @AfterMethod(groups ="SmokeTestSuite")
	@AfterMethod(alwaysRun = true)
	public void quitBrowser() throws InterruptedException {
		driver.quit();
		LoggerLoad.info("****** Browser tabs Closed Successfully ******");
		LoggerLoad.info("****** SUITE EXECUTION COMPLETED ******");
		LoggerLoad.info(
				"=================================================================================================");
		LoggerLoad.info(
				"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

	// @AfterSuite(groups ="SmokeTestSuite")
	// @AfterSuite(alwaysRun = true)
	public void dbConnectClose() {
		LoggerLoad.info("****** DB Connection Closed Successfully ******");
	}

}
