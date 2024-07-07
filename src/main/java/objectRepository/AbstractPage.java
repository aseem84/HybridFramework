/**
 * 
 */
package objectRepository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is an abstract class that represents the common methods for the page repository
 */
public abstract class AbstractPage {
	WebDriver driver;
	WebDriverWait wait;
	private static final long WAIT_TIME = 10;
	
	public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT_TIME));
    }
	
	//abstract methods
	public abstract String getPageTitle();
	public abstract String getPageHeader(WebElement element);
	public abstract WebElement getElement(WebElement element);
	public abstract String getElementText(WebElement element);

	public abstract void clickElement(WebElement element);
	public abstract void enterText(WebElement element, String text);
	
	public abstract void assertTextEquals(String actualText, String expectedText);
	public abstract void assertTextContains(String actualText, String expectedText);
	public abstract void assertElementVisible(WebElement element);
	public abstract void assertElementEnabled(WebElement element);
	public abstract void assertElementSelected(WebElement element);
	
	public abstract void waitforElementPresent(WebElement element);
	public abstract void waitforElementVisible(WebElement element);
	public abstract void waitForPageTitle(String pageTitle);
	public abstract void handleImplicitWait();
	public abstract void handleFluentWait(int timeInSeconds);
	public abstract void handlePageLoadTimeout();
	//public abstract void handleScriptTimeout(int timeInSeconds);
	
	public abstract void handleMouseHoverAction(WebElement element);
	public abstract void handleDoubleClickAction(WebElement element);
	public abstract void handleRightClickAction(WebElement element);
	public abstract void handleDragAndDropAction(WebElement sourceElement, WebElement targetElement);
	public abstract void handleScrollUpAction();
	public abstract void handleScrollDownAction();
	public abstract void scrollToElement(WebElement element);
		
	//For example, a method to handle dropdowns:
	public abstract void selectDropDownByVisibleText(WebElement element, String value);
	public abstract void selectDropDownByValue(WebElement element, String value);
	public abstract void selectDropDownByIndex(WebElement element, int index);
	public abstract void handleDropDownGeneric(WebElement element, String value);
	
	public abstract void handleSwitchToAlert();
	public abstract void getAlertText();
	public abstract void sendTextToAlert(String text);
	public abstract void acceptAlert();
	public abstract void dismissAlert();
	
	//public abstract void switchToFrame(WebElement element);
	//public abstract void switchToDefaultContent();
	
	//public abstract void handleWindowNavigation(String windowHandle);
	//public abstract void handleWindowClose();	
	
	//public abstract void takeScreenShot(String fileName);
	//public abstract void handleFileUpload(WebElement element, String filePath);
	//public abstract void scrollToElement(WebElement element);
	//public abstract void handleCookies();
	
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass){
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
