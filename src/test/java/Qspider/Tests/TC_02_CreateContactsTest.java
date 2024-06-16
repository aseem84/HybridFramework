package Qspider.Tests;

import org.testng.Assert;
import org.testng.annotations.*;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.LoggerLoad;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactsPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

@Listeners(genericUtilities.ListenerImplementation.class)
public class TC_02_CreateContactsTest extends BaseClass {
	
	HomePage homePage;
	ContactsPage ctPage;
	CreateNewContactsPage ctcPage;
	ContactInfoPage ctInfoPage;
	
	@Test(dataProvider ="excelCreatenewContacts",  groups="RegressionTestSuite")
	public void testCreateNewContacts(String firstName,String lastName) throws Throwable{
		LoginPage loginPage = new LoginPage(driver);
		homePage = loginPage.loginOperation(pfUtility.getUserName(),pfUtility.getPassword());
		       
		ctPage = homePage.clickOnContacts();
		ctcPage = ctPage.clickOnNewContacts();
		ctInfoPage = ctcPage.enterContacts(firstName, lastName);
		
		Assert.assertTrue(ctInfoPage.contactHeader().contains(firstName),"****** Contacts Creation Fails ******");
		LoggerLoad.info("****** Contacts Created Successfully ******");
		
		homePage.clickOnLogOut(driver);
		LoggerLoad.info("****** LogOut the Application Successfully ******");
		}
	
	@Test(dataProvider = "excelCreateNewContactsWithLeadSource", groups = "RegressionTestSuite")
	public void testCreateNewContactsWithLeadSource(String firstName, String lastName, String leadSource)
			throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		homePage = loginPage.loginOperation(pfUtility.getUserName(), pfUtility.getPassword());

		ctPage = homePage.clickOnContacts();
		ctcPage = ctPage.clickOnNewContacts();
		ctInfoPage = ctcPage.enterContacts(firstName, lastName, leadSource);

		Assert.assertTrue(ctInfoPage.contactHeader().contains(firstName),
				"****** Contacts with LeadSource Creation Fails ******");
		LoggerLoad.info("****** Contacts Created Successfully ******");

		homePage.clickOnLogOut(driver);
		LoggerLoad.info("****** LogOut the Application Successfully ******");
	}
	
	
	
	
	@DataProvider(name = "excelCreatenewContacts")
	public Object[][] dataGeneratorCreateContacts() throws Throwable
	{
		//String excelFilePath = ".\\src\\test\\resources\\TestData.xlsx";
		ExcelFileUtility efUtility = new ExcelFileUtility();
		Object[][] userCredentials = efUtility.readExcelData("ContactsDetails");
		return userCredentials;
	}
	
	
	
	
	@DataProvider(name = "excelCreateNewContactsWithLeadSource")
	public Object[][] dataGeneratorCreateContactsWithLeadSource() throws Throwable {
		//String excelFilePath = ".\\src\\test\\resources\\TestData.xlsx";
		ExcelFileUtility efUtility = new ExcelFileUtility();
		Object[][] userCredentials = efUtility.readExcelData("ContactsDetailsWithLeadSource");
		return userCredentials;
	}

}
