package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage extends BasePage{
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	@CacheLookup
	private WebElement headerText;

	public ContactInfoPage(WebDriver driver) {
		super(driver); // Call the constructor of BasePage class with the driver object as argument.
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeaderText() {
		return headerText;
	}
	
	public String contactHeader()
	{
		return headerText.getText();
	}
	
	

}
