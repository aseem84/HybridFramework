package objectRepository;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * This class is responsible for implementing the abstract methods of the AbstractPage
 */
public class BasePage extends AbstractPage {

	public BasePage(WebDriver driver) {
		super(driver);
	}
	private static final long PAGE_LOAD_TIMEOUT = 10;
	private static final long IMPLICIT_WAIT_TIME = 10;
	Actions action;
	Select select;
	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public String getPageHeader(WebElement element) {
		return getElement(element).getText();
	}

	@Override
	public WebElement getElement(WebElement element) {
		return element;
	}

	@Override
	public void clickElement(WebElement element) {
		element.click();		
	}

	@Override
	public void enterText(WebElement element, String text) {
		element.sendKeys(text);		
	}

	@Override
	public void assertTextEquals(String actualText, String expectedText) {
		Assert.assertEquals(actualText,expectedText,"AssertTextEquals is fail");
	}

	@Override
	public void assertTextContains(String actualText, String expectedText) {
		Assert.assertTrue(actualText.contains(expectedText),"AssertTextContains is Fail");
	}

	@Override
	public void assertElementVisible(WebElement element) {
		Assert.assertTrue(element.isDisplayed());
	}

	@Override
	public void assertElementEnabled(WebElement element) {
		Assert.assertTrue(element.isEnabled());		
	}

	@Override
	public void assertElementSelected(WebElement element) {
		Assert.assertTrue(element.isSelected());	
	}
	
	@Override
	public void handleImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));		
	}
	@Override
	public void waitforElementPresent(WebElement element) {
		//wait.until(ExpectedConditions.visibilityOf(element));
	}

	@Override
	public void waitforElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	@Override
	public void waitForPageTitle(String pageTitle) {
		wait.until(ExpectedConditions.titleContains(pageTitle));		
	}

	@Override
	public void handleFluentWait(int timeInSeconds) {
		wait = (WebDriverWait) new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);		
	}

	@Override
	public void handlePageLoadTimeout() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));		
	}

	@Override
	public void handleMouseHoverAction(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).perform();		
	}

	@Override
	public void handleDoubleClickAction(WebElement element) {
		action = new Actions(driver);
		action.doubleClick(element).perform();		
	}

	@Override
	public void handleRightClickAction(WebElement element) {
		action = new Actions(driver);
		action.contextClick(element).perform();		
	}
	
	@Override
	public void handleDragAndDropAction(WebElement sourceElement, WebElement targetElement) {
		action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();		
	}

	@Override
	public void handleScrollUpAction() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(0,-500);", "");
		
	}

	@Override
	public void handleScrollDownAction() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(0,500);", "");
		
	}	
	@Override
	public void scrollToElement(WebElement element) {
		action = new Actions(driver);
		action.scrollToElement(element).perform();
	}
	
	@Override
    public void selectDropDownByVisibleText(WebElement element, String value) {
        select = new Select(element);
        select.selectByVisibleText(value);        
    }
	@Override
    public void selectDropDownByValue(WebElement element, String value) {
		select = new Select(element);
        select.selectByValue(value);          
    }
	@Override
    public void selectDropDownByIndex(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);        
    }
	@Override
	public void handleDropDownGeneric(WebElement element, String value) {
		select = new Select(element);
		//		sl.selectByVisibleText(value);
		List<WebElement> options = select.getOptions();
		for (WebElement ddo : options) {
			//System.out.println(ddo.getText());
			if (ddo.getText().equalsIgnoreCase(value)) {
				ddo.click();
				break;
			}
		}
		
	}
	
	@Override
	public void handleSwitchToAlert() {
		driver.switchTo().alert();		
	}

	@Override
	public void getAlertText() {
		driver.switchTo().alert().getText();		
	}

	@Override
	public void sendTextToAlert(String text) {
		driver.switchTo().alert().sendKeys(text);		
	}

	@Override
	public void acceptAlert() {
		driver.switchTo().alert().accept();		
	}

	@Override
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();		
	}




	



}
