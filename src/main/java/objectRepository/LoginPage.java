package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	// This is the example of Encapsulation in Java
	WebDriver driver;
	public final String loginWarningMessage = "You must specify a valid username and password.";
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
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	
	
	
	//Business Logic methods

	public boolean validateLoginPageLogo()
	{
		return logoImg.isDisplayed();
	}
	
	public String validateLoginPageTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	
	public HomePage loginOperation(String uName, String pwd) {
		userNameBox.sendKeys(uName);
		passwordBox.sendKeys(pwd);
		loginBtn.click();
		return new HomePage(driver);
	}
	
	public String validateLoginFailMsg()
	{
		return warningMessageBox.getText();
	}

}
