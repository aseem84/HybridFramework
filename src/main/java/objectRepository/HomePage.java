package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.SeleniumUtility;
/**
 * This class will contacts all the web elements of the Home page its getter() methods
 */
public class HomePage extends SeleniumUtility {
	WebDriver driver;
	public String exptHomePageTitle = "Home";
	
	@FindBy(xpath="//a[normalize-space()='Home']")
	@CacheLookup
    private WebElement homePageTitle;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	@CacheLookup
	private WebElement adminImgSign;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	@CacheLookup
	private WebElement logOutLink;
	
	@FindBy(xpath="//a[@href='index.php?module=Leads&action=index']")
	@CacheLookup
	private WebElement leadsLink;
	
	
	@FindBy(xpath="//a[@href='index.php?module=Accounts&action=index']")
	@CacheLookup
	private WebElement organizationsLink;	
	
	@FindBy(xpath="//a[@href='index.php?module=Contacts&action=index']")
	@CacheLookup
	private WebElement conatctsLink;
	
	@FindBy(xpath="//a[@href='index.php?module=Products&action=index']")
	@CacheLookup
	private WebElement productsLink;
	
	//Constructor of the class to initiate the Web Elements
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//getter methods of web Elements
	
	public WebElement getHomePageTitle() {
        return homePageTitle;
    }
	
	public WebElement getAdminImgSign() {
		return adminImgSign;
	}

	public WebElement getLogOutLink() {
		return logOutLink;
	}
	
	public WebElement getLeadsLink() {
		return leadsLink;
	}
	
	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getConatctsLink() {
		return conatctsLink;
	}
	
	public WebElement getProductsLink() {
		return productsLink;
	}
	
	
	//Business Methods/Library/Action Methods
	
	public String validateHomePageTitle()
	{
		return homePageTitle.getText();
	}
	public void clickLeads()
	{
		leadsLink.click();
	}
	
	public void clickOrganizations()
	{
		organizationsLink.click();
	}
	
	public ContactsPage clickOnContacts() throws InterruptedException
	{
		Thread.sleep(1000);
		conatctsLink.click();
		return new ContactsPage(driver);
	}
	
	public void clickProducts()
	{
		productsLink.click();
	}
	public void clickOnLogOut(WebDriver driver) throws InterruptedException
	{
		mouseHoverAction(driver, getAdminImgSign());
		Thread.sleep(1000);
		logOutLink.click();
	}
	
}
