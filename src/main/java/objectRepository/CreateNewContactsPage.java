package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactsPage extends BasePage {
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
		//this.driver = driver;
		super(driver);
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
		enterText(getfNameBox(),fName);
		enterText(getfNameBox(),lName);
		clickElement(getSaveBtn());
		Thread.sleep(1000);
		
		return getInstance(ContactInfoPage.class);
		
		//return new ContactInfoPage(driver);
	}
	
	public ContactInfoPage enterContacts(String fName, String lName, String leadSource) throws InterruptedException
	{
		enterText(getfNameBox(),fName);
		enterText(getfNameBox(),lName);
		handleDropDownGeneric(getDropDownLeadSource(), leadSource);
		clickElement(getSaveBtn());
		//saveBtn.click();
		Thread.sleep(1000);
		return getInstance(ContactInfoPage.class);		
		//return new ContactInfoPage(driver);
	}
}
