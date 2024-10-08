package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	// This is the example of Encapsulation in Java
	WebDriver driver;

	public final String loginWarningMessage = "You must specify a valid username and password.";
	public final String expectedLoginPageTitle = "maverick";
	// rule-01: Create separate page for each page in the Web Application

	// Rule-02: Identify the web elements using @FindBy and make them private

	@FindBy(xpath = "//img[@alt='logo']")
	@CacheLookup
	private WebElement logoImg;

	@FindBy(xpath = "//input[@name='user_name']")
	@CacheLookup
	private WebElement userNameBox;

	@FindBy(xpath = "//input[@name='user_password']")
	@CacheLookup
	private WebElement passwordBox;

	@FindBy(xpath = "//input[@id='submitButton']")
	@CacheLookup
	private WebElement loginBtn;

	@FindBy(xpath = "//div[@class='errorMessage']")
	@CacheLookup
	private WebElement warningMessageBox;

	// Rule-03: Create a constructor for initialization
	public LoginPage(WebDriver driver) {
		//this.driver = driver;
		super(driver);
		//PageFactory.initElements(driver, this);
	}

	// Rule-04: Generate getter() methods of each element in the page

	public WebElement getLogoImg() {
		return logoImg;
	}

	public WebElement getUserNameBox() {
		return userNameBox;
	}

	public WebElement getPasswordBox() {
		return passwordBox;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public WebElement getWarningMessageBox() {
		return warningMessageBox;
	}

	// Rule-05: BusinessLogic methods

	public boolean validateLoginPageLogo() {
		return getLogoImg().isDisplayed();
	}

	public String getLoginPageTitle() {
		return getPageTitle();
	}
	
	public String validateLoginPageHeader() {
		return getPageHeader(loginBtn);
	}
	
// Method Overloading Examples in Selenium with java
	public HomePage loginOperation(String uName, String pwd) {
		if(getUserNameBox().isDisplayed() && getPasswordBox().isDisplayed()) {
		getUserNameBox().sendKeys(uName);
		getPasswordBox().sendKeys(pwd);
		getLoginBtn().click();
		}
		return getInstance(HomePage.class);
		//return new HomePage(driver);
	}
	public HomePage loginOperation() {
		getUserNameBox().sendKeys("");
		getPasswordBox().sendKeys("");
		getLoginBtn().click();
		return getInstance(HomePage.class);
	}
	// comment for the following methodsn
	public HomePage loginOperation(String userCredentials) {
		getUserNameBox().sendKeys(userCredentials.split(":")[0]);
		getPasswordBox().sendKeys(userCredentials.split(":")[1]);
		getLoginBtn().click();
		return getInstance(HomePage.class);
	}

	public String getLoginFailMsg() {
		return getElementText(getWarningMessageBox());
	}
	


}
