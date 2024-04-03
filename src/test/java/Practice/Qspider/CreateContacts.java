package Practice.Qspider;

import org.openqa.selenium.WebDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactsPage;
import objectRepository.HomePage;

public class CreateContacts {
	
	
	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;

		ExcelFileUtility ref = new ExcelFileUtility();
		String lastName = ref.readExcelData("TestData", 1, 2);
		String firstName = "Aseem";

		driver = LaunchBrowser.launchBrowserMethod();
		LoginApplication.login(driver);
		boolean status = createContacts(driver, firstName, lastName);

		if (status) {
			System.out.println("Contacts Creation Successful...");
		} else {
			System.err.println("Contacts Creation Fail...");
		}
		SeleniumUtility.quitBrowser(driver);
	}

	public static boolean createContacts(WebDriver driver, String firstName, String lastName) throws Throwable {
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
		ctcPg.enterContacts(firstName, lastName);
		Thread.sleep(1000);

		String contactHeaderInfo = ctInfoPage.contactHeader();
		if (contactHeaderInfo.contains(lastName)) {
			System.out.println(contactHeaderInfo + " Created...");
			homePg.clickOnContacts();
		}
		homePg.clickOnLogOut(driver);
		return true;
	}

}
