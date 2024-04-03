package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DeleteContacts {

	public static void main(String[] args) throws Throwable {

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
			Thread.sleep(1000);
			 int rowSize = driver.findElements(By.xpath("//table[@class='lvt small']//tr")).size();
			 System.out.println(rowSize);
			 while(rowSize>2) {
				 Thread.sleep(2000);
				 String contID = driver.findElement(By.xpath("//table[@class='lvt small']//tr["+rowSize+"]//td[2]")).getText();
				if(contID.contains("CON"))
				{
					driver.findElement(By.xpath("//table[@class='lvt small']//tr["+rowSize+"]//td[1]/input")).click();
					System.out.println(contID +"will be deleted...");
					Thread.sleep(1000);
				}
				 
				 rowSize--;
			 }
			 driver.findElement(By.xpath("//table[@class='small']//td/input[@value='Delete']")).click();
			 driver.switchTo().alert().accept();
			
			// driver.close();

		}

	}

}
