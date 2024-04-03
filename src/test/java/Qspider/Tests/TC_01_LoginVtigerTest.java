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
	
	
	@Test(priority = 1, dataProvider= "excelLOginCredentials", groups = "SmokeTestSuite")
	public void testLoginWithAllCombinationOfCredentials(String uName, String pwd, String status) throws Throwable {

		LoginPage loginPage = new LoginPage(driver);
		homePage= loginPage.loginOperation(uName, pwd);
		
		// Validate Login Successful
		switch(status) {
			case "vUserVPass":
				Assert.assertEquals(homePage.validateHomePageTitle(), homePage.exptHomePageTitle,"****** Login Fail: HomePage Title Missmatch ******");
				LoggerLoad.info("****** After Login Home Page Loaded Successfully ****** ");
				homePage.clickOnLogOut(driver);
				LoggerLoad.info("****** LogOut the Application Successfully ******");
				break;
			case "ivUserVPass":
				Assert.assertEquals(loginPage.validateLoginFailMsg(),loginPage.loginWarningMessage,"****** Login Fail: Invalid UserName and Correct Password ******");
				LoggerLoad.warn("****** Login Fail: "+loginPage.validateLoginFailMsg()+" ******");
				break;
			case "vUserIvPass":
				Assert.assertEquals(loginPage.validateLoginFailMsg(),loginPage.loginWarningMessage,"****** Login Fail: Invalid UserName and Correct Password ******");
				LoggerLoad.warn("****** Login Fail: "+loginPage.validateLoginFailMsg()+" ******");
				break;
			case "ivUserIvPass":
				Assert.assertEquals(loginPage.validateLoginFailMsg(),loginPage.loginWarningMessage,"****** Login Fail: Invalid UserName and Correct Password ******");
				LoggerLoad.warn("****** Login Fail: "+loginPage.validateLoginFailMsg()+" ******");
				break;
			default:
				LoggerLoad.warn("****** Login Fail: BLANK UserName and Password ******");
		}
	}
	
	@Test(priority = 2)
	public void validateLoginPageTitle()
	{
		LoginPage loginPg = new LoginPage(driver);
		String loginPageTitle = loginPg.validateLoginPageTitle(driver);
		Assert.assertEquals(loginPageTitle, "vtiger CRM 5 - Commercial Open Source CRM","****** Login Page Title Mismatch ******");
		LoggerLoad.info("****** login Page Title matched: "+loginPageTitle+" ******");
	}

	@Test(priority = 3)
	public void validateLoginPageTitlLogo()
	{
		LoginPage loginPg = new LoginPage(driver);
		boolean loginPageTitle = loginPg.validateLoginPageLogo();
		Assert.assertTrue(loginPageTitle,"****** Login Page Logo Not Displayed ******");
		LoggerLoad.info("****** login Page Logo Visible: "+loginPageTitle+"****** ");
	}	

	@Test(priority = 4, groups = {"SmokeTestSuite","RegressionTestSuite"},retryAnalyzer = genericUtilities.RetryAnalyserImplementation.class)
	public void testLoginWithValidCredentials() throws Throwable {

		LoginPage loginPg = new LoginPage(driver);
		homePage = loginPg.loginOperation(pfUtility.getUserName(), pfUtility.getPassword());

		// Validate Login Successful
		Assert.assertEquals(homePage.validateHomePageTitle(), homePage.exptHomePageTitle,"****** Login Fail: HomePage Title Missmatch ******");
		LoggerLoad.info("****** After Login Home Page Loaded Successfully ****** ");

		homePage.clickOnLogOut(driver);
		LoggerLoad.info("****** LogOut the Application Successfully ******");
	}
	
	@DataProvider(name = "excelLOginCredentials")
	public Object[][] loginDataGenerator() throws Throwable
	{
		ExcelFileUtility efUtility = new ExcelFileUtility();
		Object[][] userCredentials = efUtility.readExcelData("UserCredentials");
		
		return userCredentials;
	}
}
