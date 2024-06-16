package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.SeleniumUtility;

public class CreateNewContactsPage extends SeleniumUtility {
	WebDriver driver;
	@FindBy(xpath="//input[@name='firstname']")
	@CacheLookup
	private WebElement fNameBox;
	
	@FindBy(xpath="//input[@name='lastname']")
	@CacheLookup
	private WebElement lNamebox;
	
	@FindBy(xpath="//input[contains(@class,'crmButton small save')]")
	@CacheLookup
	private WebElement saveBtn;
	
	@FindBy(name="leadsource")
	@CacheLookup
	private WebElement dropDownLeadSource;
	
	//Getter methods for all the elements
	public CreateNewContactsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getfNameBox() {
		return fNameBox;
	}

	public WebElement getlNamebox() {
		return lNamebox;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getDropDownLeadSource() {
		return dropDownLeadSource;
	}

	public ContactInfoPage enterContacts(String fName,String lName) throws InterruptedException
	{
		getfNameBox().sendKeys(fName); 
		getlNamebox().sendKeys(lName);
		getSaveBtn().click();
		Thread.sleep(1000);
		return new ContactInfoPage(driver);
	}
	
	public ContactInfoPage enterContacts(String fName, String lName, String leadSource) throws InterruptedException
	{
		fNameBox.sendKeys(fName);
		lNamebox.sendKeys(lName);
		handleDropDownGeneric(dropDownLeadSource, leadSource);
		saveBtn.click();
		Thread.sleep(1000);
		return new ContactInfoPage(driver);
	}
}
