package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class contains generic methods related to selenium web driver
 * @author aseem
 */
public class SeleniumUtility {
	private static final long PAGE_LOAD_TIMEOUT = 20;
	private static final long IMPLICIT_WAIT_TIME = 20;
	/**
	 * This method will maximize the Window
	 * 
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method will minimize the Window
	 * 
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will delete All the cookies in the browser
	 * @param driver
	 */
	public void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	/**
	 * This method will implicitly wait for 10 sec to execute a statement
	 * 
	 * @param driver
	 */
	public void addImplicitlyWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
	}
	
	/**
	 * This method will implicitly wait for 10 sec. to Load a Page
	 * @param driver
	 */
	public void addpageLoadTimeoutWait(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
	}

	/**
	 * This method will explicitly wait for 20 sec to execute a statement
	 * 
	 * @param driver
	 */
	public void addExplicitlyWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This will handle drop down by index
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropDownByIndex(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	/**
	 * This will handle drop down by Value
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropDownByValue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	/**
	 * This will handle drop down by Visible Text
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropDownByVisibleText(String value, WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(value);
	}

	/**
	 * This is a generic method to use DropDown options
	 * @param element
	 * @param value
	 */
	public void handleDropDownGeneric(WebElement element, String value) {
		Select sl = new Select(element);
		//		sl.selectByVisibleText(value);
		List<WebElement> options = sl.getOptions();
		for (WebElement ddo : options) {
			//System.out.println(ddo.getText());
			if (ddo.getText().equalsIgnoreCase(value)) {
				ddo.click();
				break;
			}
		}
	}

	/**
	 * This method will perform mouse Hover action on a web element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	/**
	 * This method will perform right click action on web page
	 * 
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}

	/**
	 * This method will perform Double click on web page
	 * 
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver) {
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}

	/**
	 * This method will perform drag and Drop Action
	 * 
	 * @param driver
	 * @param src
	 * @param dst
	 */
	public void dragAndDropAction(WebDriver driver, WebElement src, WebElement dst) {
		Actions act = new Actions(driver);
		act.dragAndDrop(src, dst).perform();
	}

	/**
	 * This method will scroll down by 500 Unit
	 * 
	 * @param driver
	 */
	public void scrollDownAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(0,500);", "");

	}

	/**
	 * This method will scroll up by 500 Unit
	 * 
	 * @param driver
	 */
	public void scrollUpAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(0,-500);", "");
	}

	/**
	 * This method will scroll up and down upto the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void scrollToElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}

	/**
	 * This method will handle Frame by name or ID
	 * 
	 * @param driver
	 * @param nameOeID
	 */
	public void handleFrame(WebDriver driver, String nameOeID) {
		driver.switchTo().frame(nameOeID);
	}

	/**
	 * This method will handle Frame by WebElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void handleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method will accept the alert popup
	 * 
	 * @param driver
	 */
	public void alertOK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method will Cancel the alert popup
	 * 
	 * @param driver
	 */
	public void alertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method will enter a text into the Prompt popup
	 * 
	 * @param driver
	 * @param value
	 */
	public void alertText(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	/**
	 * This method will return the text from popup
	 * 
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	/**
	 * This method will capture the screenshot and return the path to the caller
	 * 
	 * @param driver
	 * @param ssName
	 * @return
	 * @throws IOException
	 */
	public String captureScreenShot(WebDriver driver, String ssName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\ScreenShots\\" + ssName + ".png");
		/**
		 * FileUtils.copyFile(src, dest); or Files.copy(src, dst); need
		 * https://mvnrepository.com/artifact/commons-io/commons-io dependency
		 */
		Files.copy(src, dst);// need https://mvnrepository.com/artifact/commons-io/commons-io dependency
		return dst.getAbsolutePath(); // used for extent report
	}
	public void sendEmailReport(File pathExternReport) {
	    try {
	        // Prepare the email
	        ImageHtmlEmail email = new ImageHtmlEmail();
	        email.setHostName("smtp.gmail.com");
	        email.setSmtpPort(465);
	        email.setAuthenticator(new DefaultAuthenticator("mfsi.aseemk@gmail.com", "S0n1@smith"));
	        email.setSSLOnConnect(true);
	        email.setFrom("your_email@domain.com");
	        email.setSubject("Test Automation Report");
	        email.setMsg("Please find the attached Report....");
	        email.addTo("aseemk.patel@gmail.com");

	        // Create the attachment
	        EmailAttachment attachment = new EmailAttachment();
	        attachment.setPath(pathExternReport.getAbsolutePath());
	        attachment.setDisposition(EmailAttachment.ATTACHMENT);
	        attachment.setDescription("Extent Report");
	        attachment.setName("Report.html");

	        // Attach the file to the email
	        email.attach(attachment);

	        // Send the email
	        email.send();
	    } catch (EmailException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * This method will closes single tab/window ,driver currently focusing
	 * 
	 * @param driver
	 * @throws InterruptedException 
	 */
	public static void closeBrowserTab(WebDriver driver) {
		driver.close();
	}

	/**
	 * This method will closes all the tabs/windows including browser
	 * 
	 * @param driver
	 * @return 
	 */
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}
