package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContacts {

	public static void main(String[] args) throws Throwable {

		String firstName = "Aseem";
		String lastName = "Patel";
		// Lunching if Chrome Browser and Open the Url
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/index.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Login to vtiger Website
		WebElement inputBox = driver.findElement(By.xpath("//input[@name='user_name']"));
		Actions act = new Actions(driver);
		act.moveToElement(inputBox).click().sendKeys("admin", Keys.TAB).sendKeys("admin", Keys.ENTER).build().perform();

		String exptTitle = "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(exptTitle)) {
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
			Thread.sleep(2000);
			for (int i = 0; i < 5; i++) {
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName + i);
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName + i);
				driver.findElement(By.xpath("//input[contains(@class,'crmButton small save')]")).click();
				Thread.sleep(2000);
				String userName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (userName.contains(firstName)) {
					System.out.println(i+1 + " " + userName + " Created...");
				}
				Thread.sleep(1000);
			}
			driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
			// driver.close();

		}
	}

}
