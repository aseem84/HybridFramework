package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginVtiger {

	public static void main(String[] args) throws Throwable {
		//Lunching if Chrome Browser and Open the Url
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/index.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Login to vtiger Website
		WebElement inputBox = driver.findElement(By.xpath("//input[@name='user_name']"));
		Actions act = new Actions(driver);
		act.moveToElement(inputBox).click().sendKeys("admin",Keys.TAB).sendKeys("admin",Keys.ENTER).build().perform();
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebElement logOut = driver.findElement(By.xpath("//a[text()='Sign Out']"));
		
		Thread.sleep(2000);
		act.moveToElement(signOut).moveToElement(logOut).click().build().perform();
		
		driver.close();
		

	}

}
