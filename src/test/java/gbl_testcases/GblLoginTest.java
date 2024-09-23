package gbl_testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GblLoginTest {
	
	@Test
	public void loginTest() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://qa.maverickgames.com/");
		
		driver.findElement(By.xpath("//button[@aria-label='login button']")).click();
		//driver.findElement(By.xpath("//button[@aria-label='button-close']")).click();
		//System.out.println("Close button working Successfully....");
		
		//code for login
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aseem123@yopmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("aseem@123");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		boolean btnStatus = driver.findElement(By.xpath("//button[@aria-label='login submit button']")).isEnabled();
		Assert.assertTrue(btnStatus);
		driver.findElement(By.xpath("//button[@aria-label='login submit button']")).click();
		
		Thread.sleep(2000);
		String loginStatus = driver.findElement(By.xpath("//div[contains(@class,'deposit')]/preceding-sibling::div//a")).getText();
		System.out.println("Element is..."+loginStatus);
		Assert.assertEquals(loginStatus, "aseem123");
		
		driver.quit();
		}

}
