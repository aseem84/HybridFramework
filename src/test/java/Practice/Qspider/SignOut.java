package Practice.Qspider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SignOut {
	public static void logOut(WebDriver driver) throws Throwable {
		
		// Logout to vtiger Website

		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebElement logOut1 = driver.findElement(By.xpath("//a[text()='Sign Out']"));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(signOut).moveToElement(logOut1).click().perform();
		System.out.println("LogOut Successful...");
	}

}
