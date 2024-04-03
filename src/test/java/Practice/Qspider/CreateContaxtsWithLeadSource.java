package Practice.Qspider;

import org.openqa.selenium.WebDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactsPage;
import objectRepository.HomePage;

public class CreateContaxtsWithLeadSource 
{

	public static void main(String[] args) throws Throwable 
	{
		WebDriver driver = null;

		
		ExcelFileUtility ref = new ExcelFileUtility();
		String firstName = "Aseem";
		String lastName = ref.readExcelData("TestData", 9, 2);
		String leadSource = ref.readExcelData("TestData", 9, 3);
		
		// lunch the browser and perform the login operation
		driver = LaunchBrowser.launchBrowserMethod();
		LoginApplication.login(driver);
		
		HomePage homePg = new HomePage(driver);
		ContactsPage ctPg = new ContactsPage(driver);
		CreateNewContactsPage ctcPg = new CreateNewContactsPage(driver);
		ContactInfoPage ctInfoPage = new ContactInfoPage(driver);
		
		homePg.clickOnContacts();
		System.out.println("Contacts Link Clicked Successfully");
		Thread.sleep(1000);
		ctPg.clickOnNewContacts();
		System.out.println("Add Contacts Link Clicked Successfully");
		Thread.sleep(1000);
		ctcPg.enterContacts(firstName,lastName, leadSource);
		Thread.sleep(1000);
		
		// validate the successful creation of contact
		String contactHeaderInfo = ctInfoPage.contactHeader();
		
		if (contactHeaderInfo.contains(lastName)) 
		{
			System.out.println(contactHeaderInfo + " Created with Lead Source...");
		}
		else 
		{
			System.err.println("Contact Creationd with Lead Source Fail...");
		}
		
		// close the browser
		homePg.clickOnLogOut(driver);
		Thread.sleep(1000);
		SeleniumUtility.quitBrowser(driver);
	}

}
