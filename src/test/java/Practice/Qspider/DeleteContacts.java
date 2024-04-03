package Practice.Qspider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericUtilities.SeleniumUtility;

public class DeleteContacts {

	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;

		// Lunching the Browser and login
		driver = LaunchBrowser.launchBrowserMethod();
		LoginApplication.login(driver);
		// Click the contacts link
		driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
		Thread.sleep(1000);
		int rowSize = driver.findElements(By.xpath("//table[@class='lvt small']//tr")).size();
		
		// call the method to Delete the contacts
		boolean status = deleteAllContacts(driver, rowSize);

		// Validate the contact deleting Process
		if (status) {
			System.out.println("All Contacts Deleted Successfully...");
		} else {
			System.err.println("No Contacts to Deletion...");
		}
		// Logout the Application
		SignOut.logOut(driver);
		
		// Close the browser
		// SeleniumUtility.closeBrowser(driver);

	}

	public static boolean deleteAllContacts(WebDriver driver, int rowSize) throws InterruptedException {
		SeleniumUtility sUtil = new SeleniumUtility();
		while (rowSize > 2) {
			Thread.sleep(2000);
			String contID = driver.findElement(By.xpath("//table[@class='lvt small']//tr[" + rowSize + "]//td[2]"))
					.getText();
			if (contID.contains("CON")) {
				driver.findElement(By.xpath("//table[@class='lvt small']//tr[" + rowSize + "]//td[1]/input")).click();
				System.out.println(contID + " will be deleted...");
				Thread.sleep(1000);
			}

			rowSize--;
		}
		driver.findElement(By.xpath("//table[@class='small']//td/input[@value='Delete']")).click();
		sUtil.alertOK(driver);
		return true;
	}

}
