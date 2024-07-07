package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//a[@class='hdrLink']")
	@CacheLookup
    private WebElement contactsHeaderText;
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	@CacheLookup
	private WebElement createConatctsLink;
	
	public ContactsPage(WebDriver driver)
	{
		//this.driver = driver;
		super(driver);
        PageFactory.initElements(driver, this);
	}

	public WebElement getCreateConatctsLink() {
		return createConatctsLink;
	}
	
	public WebElement getContactsHeaderText() {
        return contactsHeaderText;
    }
	
	//Action/Business Logic
	
	public String validateContactsHeader()
	{
		return getPageHeader(getContactsHeaderText());
		//return contactsHeaderText.getText();
	}
	
	public CreateNewContactsPage clickOnNewContacts() throws InterruptedException
	{
		Thread.sleep(1000);
		clickElement(getContactsHeaderText());
		//createConatctsLink.click();
		return getInstance(CreateNewContactsPage.class);
		//return new CreateNewContactsPage(driver);
	}
}
