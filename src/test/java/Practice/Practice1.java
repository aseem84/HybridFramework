package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Practice1 {
	@Test
	public void test() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html");
		
		String propertiesFilePath = ".\\src\\test\\resources\\config.properties";
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		Properties properties = new Properties();
		properties.load(fis);
		System.out.println(properties.getProperty("browser"));
		System.out.println(properties.getProperty("URL"));
		System.out.println(properties.getProperty("uName"));
		System.out.println(properties.getProperty("password"));
		WebElement element = driver.findElement(By.xpath("//select[@id='course']"));
		WebDriverWait ewait = new WebDriverWait(driver,Duration.ofSeconds(10));
		ewait.until(ExpectedConditions.visibilityOf(element));

		
		Select select =new Select(element);
		List<WebElement> list = select.getOptions();
		for(WebElement a:list) {
			if(a.getText().equalsIgnoreCase("Java")) {
			System.out.println(a.getText());
			a.click();
			break;}
		}
		
		driver.quit();
	}
}
