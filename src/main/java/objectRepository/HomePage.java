package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class will contacts all the web elements of the Home page its getter()
 * methods
 */
public class HomePage extends BasePage {
	WebDriver driver;
	public String exptHomePageTitle = "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM";

	@FindBy(xpath = "//a[normalize-space()='Home']")
	@CacheLookup
	private WebElement homePageTitle;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	@CacheLookup
	private WebElement adminImgSign;

	@FindBy(xpath = "//a[text()='Sign Out']")
	@CacheLookup
	private WebElement logOutLink;

	@FindBy(xpath = "//a[@href='index.php?module=Leads&action=index']")
	@CacheLookup
	private WebElement leadsLink;

	@FindBy(xpath = "//a[@href='index.php?module=Accounts&action=index']")
	@CacheLookup
	private WebElement organizationsLink;

	@FindBy(xpath = "//a[@href='index.php?module=Contacts&action=index']")
	@CacheLookup
	private WebElement conatctsLink;

	@FindBy(xpath = "//a[@href='index.php?module=Products&action=index']")
	@CacheLookup
	private WebElement productsLink;

	// Constructor of the class to initiate the Web Elements
	public HomePage(WebDriver driver) {
		super(driver);
		// this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// getter methods of web Elements

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

	// Business Methods/Library/Action Methods

	public String getHomePageTitleText() {
		return getPageTitle();
		// return homePageTitle.getText();
	}

	public void clickLeads() {
		clickElement(getLeadsLink());
	}

	public void clickOrganizations() {
		clickElement(getOrganizationsLink());
		// organizationsLink.click();
	}

	public ContactsPage clickOnContacts() throws InterruptedException {
		Thread.sleep(1000);
		// conatctsLink.click();
		clickElement(getConatctsLink());

		// return new ContactsPage(driver);
		return getInstance(ContactsPage.class);
	}

	public void clickProducts() {
		clickElement(getProductsLink());
		// productsLink.click();
	}

	public void clickOnLogOut(WebDriver driver) throws InterruptedException {
		handleMouseHoverAction(getAdminImgSign());
		Thread.sleep(1000);
		clickElement(getLogOutLink());
		// logOutLink.click();
	}

	public boolean isMyHomePageExist(String exptHomePageTitle) {
		if(exptHomePageTitle.equals(getHomePageTitleText()))
		{
			try {
				return true;	
			}
			catch (Exception e) {
                return false;
            }
			
		}
		else {
			return false;
		}
		
	}

}