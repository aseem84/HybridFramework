package Qspider.Tests;

import org.testng.Assert;
import org.testng.annotations.*;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.LoggerLoad;
import objectRepository.HomePage;
import objectRepository.LoginPage;

@Listeners(genericUtilities.ListenerImplementation.class)
public class TC_01_LoginVtigerTest extends BaseClass {
	HomePage homePage;
	LoginPage loginPage;

	@Test(priority = 1, dataProvider = "excelLOginCredentials", groups = "SmokeTestSuite")
	public void testLoginWithAllCombinationOfCredentials(String uName, String pwd, String status) throws Throwable {

		loginPage = new LoginPage(driver);
		homePage = loginPage.loginOperation(uName, pwd);

		// Validate Login Successful
		switch (status) {
		case "vUserVPass":
			Assert.assertEquals(homePage.getHomePageTitleText(), homePage.exptHomePageTitle,
					"****** Login Fail: HomePage Title Missmatch ******");
			LoggerLoad.info("****** After Login Home Page Loaded Successfully ****** ");
			homePage.clickOnLogOut(driver);
			LoggerLoad.info("****** LogOut the Application Successfully ******");
			break;
		case "ivUserVPass":
			Assert.assertEquals(loginPage.getLoginFailMsg(), loginPage.loginWarningMessage,
					"****** Login Fail: Invalid UserName and Correct Password ******");
			LoggerLoad.warn("****** Login Fail: " + loginPage.getLoginFailMsg() + " ******");
			break;
		case "vUserIvPass":
			Assert.assertEquals(loginPage.getLoginFailMsg(), loginPage.loginWarningMessage,
					"****** Login Fail: Invalid UserName and Correct Password ******");
			LoggerLoad.warn("****** Login Fail: " + loginPage.getLoginFailMsg() + " ******");
			break;
		case "ivUserIvPass":
			Assert.assertEquals(loginPage.getLoginFailMsg(), loginPage.loginWarningMessage,
					"****** Login Fail: Invalid UserName and Correct Password ******");
			LoggerLoad.warn("****** Login Fail: " + loginPage.getLoginFailMsg() + " ******");
			break;
		default:
			LoggerLoad.warn("****** Login Fail: BLANK UserName and Password ******");
		}
	}

	@Test(priority = 2)
	public void validateLoginPageTitle() {
		LoggerLoad.info("****** Validating login page title ****** ");

		loginPage = new LoginPage(driver);
		String actualLoginPageTitle = loginPage.getLoginPageTitle();
		String expectedLoginPageTitle = loginPage.expectedLoginPageTitle;

		try {
			Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle, "Login Page Title mismatch.");
			LoggerLoad.info("****** Login Page Title matched: " + actualLoginPageTitle + " ******");
		} catch (AssertionError e) {
			LoggerLoad.error("****** Test Failed: Login Page Title Mismatch ******");
			LoggerLoad.error("Expected: " + expectedLoginPageTitle + " | Actual: " + actualLoginPageTitle);
			LoggerLoad.error("Stack Trace: "+ e);
			throw e; 
		}
	}

	@Test(priority = 3)
	public void validateLoginPageTitlLogo() {
        LoggerLoad.info("****** Validating login page logo visibility ****** ");

		loginPage = new LoginPage(driver);
		try {
            boolean loginPageLogoStatus = loginPage.validateLoginPageLogo();  // Get logo visibility status
            Assert.assertTrue(loginPageLogoStatus, "****** Login Page Logo Not Displayed ******");
            LoggerLoad.info("****** Login Page Logo is visible ******");
        } catch (AssertionError e) {
            LoggerLoad.error("****** Test Failed: Login Page Logo is NOT visible ******"+ e);
            throw e;  
        }
	}

	@Test(priority = 4, groups = { "SmokeTestSuite",
			"RegressionTestSuite" }, retryAnalyzer = genericUtilities.RetryAnalyserImplementation.class)
	public void testLoginWithValidCredentials() throws Throwable {
        LoggerLoad.info(" ****** Starting test: Login with valid credentials ******");

		loginPage = new LoginPage(driver);
		homePage = loginPage.loginOperation(pfUtility.getUserName(), pfUtility.getPassword());

		// Validate Login Successful
		Assert.assertTrue(homePage.isMyHomePageExist(homePage.exptHomePageTitle));
		// Assert.assertEquals(homePage.getHomePageTitleText(),
		// expectedHomePageTitle,"****** Login Fail: HomePage Title Missmatch ******");
		LoggerLoad.info("****** After Login Home Page Loaded Successfully ****** ");

		homePage.clickOnLogOut(driver);
		LoggerLoad.info("****** LogOut the Application Successfully ******");
	}

	@DataProvider(name = "excelLOginCredentials")
	public Object[][] loginDataGenerator() throws Throwable {
		ExcelFileUtility efUtility = new ExcelFileUtility();
		Object[][] userCredentials = efUtility.readExcelData("UserCredentials");

		return userCredentials;
	}
}
