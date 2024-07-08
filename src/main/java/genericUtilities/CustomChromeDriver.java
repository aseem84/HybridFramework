package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class will inherit the ChromeDriver class and override all the methods
 * along with the new functionalities
 * 
 * @author aseem patel
 * @version v0.1
 * @since 8.7.2024
 */
public class CustomChromeDriver extends ChromeDriver {
	SeleniumUtility sUtils = new SeleniumUtility();

	public CustomChromeDriver() {
		super();
	}

	// override get methods of ChromeDriver class
	@Override
	public void get(String url) {
		sUtils.maximizeWindow(this);
		sUtils.deleteAllCookies(this);
		sUtils.addImplicitlyWait(this);
		sUtils.addpageLoadTimeoutWait(this);
		super.get(url);
		System.out.println("Chrome Browser Navigated to: " + url);
	}

	// override getCurrentUrl() method
	@Override
	public String getCurrentUrl() {
		System.out.println("Current URL: " + super.getCurrentUrl());
		return super.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		System.out.println("Getting title: "+ super.getTitle());
		return super.getTitle();
	}

	@Override
	public String getPageSource() {
		System.out.println("Getting page source");
		return super.getPageSource();
	}

	// override FindElement method of WebDriver class
	@Override
	public WebElement findElement(By by) {
		System.out.println("Finding element using: " + by.toString());
		return super.findElement(by);
	}

	// override findElements method of WebDriver class
	@Override
	public List<WebElement> findElements(By by) {
		System.out.println("Finding elements using: " + by.toString());
		return super.findElements(by);
	}

	@Override
	public Set<String> getWindowHandles() {
		System.out.println("Getting window handles");
		return super.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		System.out.println("Getting window handle");
		return super.getWindowHandle();
	}

	@Override
	public void close() {
		System.out.println("Closing the browser");
		super.close();
	}

	@Override
	public void quit() {
		super.quit();
		System.out.println("Quitting the browser");
	}

	// Additional methods for waiting, executing scripts, etc.
	public WebElement waitForElement(By by, int timeoutInSeconds) {
		System.out.println("Waiting for element by: " + by);
		WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void executeJavaScript(String script, Object... args) {
		System.out.println("Executing JavaScript: " + script);
		if (this instanceof JavascriptExecutor) {
			((JavascriptExecutor) this).executeScript(script, args);
		} else {
			throw new WebDriverException("Driver does not support JavaScript execution");
		}
	}

	public void takeScreenshot(String filePath) {
		System.out.println("Taking screenshot");
		if (this instanceof TakesScreenshot) {
			TakesScreenshot screenshotTaker = (TakesScreenshot) this;
			File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new WebDriverException("Driver does not support taking screenshots");
		}
	}
}

class CustomFirefoxDriver extends FirefoxDriver {
	SeleniumUtility sUtils = new SeleniumUtility();

	public CustomFirefoxDriver() {
		super();
	}

	// override get methods of ChromeDriver class
	@Override
	public void get(String url) {
		sUtils.maximizeWindow(this);
		sUtils.deleteAllCookies(this);
		sUtils.addImplicitlyWait(this);
		sUtils.addpageLoadTimeoutWait(this);
		super.get(url);
		System.out.println("Firefox Browser Navigated to: " + url);
	}

	// override getCurrentUrl() method
	@Override
	public String getCurrentUrl() {
		System.out.println("Current URL: " + super.getCurrentUrl());
		return super.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		System.out.println("Getting title: "+ super.getTitle());
		return super.getTitle();
	}

	@Override
	public String getPageSource() {
		System.out.println("Getting page source");
		return super.getPageSource();
	}

	// override FindElement method of WebDriver class
	@Override
	public WebElement findElement(By by) {
		System.out.println("Finding element using: " + by.toString());
		return super.findElement(by);
	}

	// override findElements method of WebDriver class
	@Override
	public List<WebElement> findElements(By by) {
		System.out.println("Finding elements using: " + by.toString());
		return super.findElements(by);
	}

	@Override
	public Set<String> getWindowHandles() {
		System.out.println("Getting window handles");
		return super.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		System.out.println("Getting window handle");
		return super.getWindowHandle();
	}

	@Override
	public void close() {
		System.out.println("Closing the browser");
		super.close();
	}

	@Override
	public void quit() {
		super.quit();
		System.out.println("Quitting the browser");
	}

	// Additional methods for waiting, executing scripts, etc.
	public WebElement waitForElement(By by, int timeoutInSeconds) {
		System.out.println("Waiting for element by: " + by);
		WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void executeJavaScript(String script, Object... args) {
		System.out.println("Executing JavaScript: " + script);
		if (this instanceof JavascriptExecutor) {
			((JavascriptExecutor) this).executeScript(script, args);
		} else {
			throw new WebDriverException("Driver does not support JavaScript execution");
		}
	}

	public void takeScreenshot(String filePath) {
		System.out.println("Taking screenshot");
		if (this instanceof TakesScreenshot) {
			TakesScreenshot screenshotTaker = (TakesScreenshot) this;
			File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new WebDriverException("Driver does not support taking screenshots");
		}
	}
}

class CustomEdgeDriver extends EdgeDriver {
	SeleniumUtility sUtils = new SeleniumUtility();

	public CustomEdgeDriver() {
		super();
	}

	// override get methods of ChromeDriver class
	@Override
	public void get(String url) {
		sUtils.maximizeWindow(this);
		sUtils.deleteAllCookies(this);
		sUtils.addImplicitlyWait(this);
		sUtils.addpageLoadTimeoutWait(this);
		super.get(url);
		System.out.println("Edge Browser Navigated to: " + url);
	}

	// override getCurrentUrl() method
	@Override
	public String getCurrentUrl() {
		System.out.println("Current URL: " + super.getCurrentUrl());
		return super.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		System.out.println("Getting title: "+ super.getTitle());
		return super.getTitle();
	}

	@Override
	public String getPageSource() {
		System.out.println("Getting page source");
		return super.getPageSource();
	}

	// override FindElement method of WebDriver class
	@Override
	public WebElement findElement(By by) {
		System.out.println("Finding element using: " + by.toString());
		return super.findElement(by);
	}

	// override findElements method of WebDriver class
	@Override
	public List<WebElement> findElements(By by) {
		System.out.println("Finding elements using: " + by.toString());
		return super.findElements(by);
	}

	@Override
	public Set<String> getWindowHandles() {
		System.out.println("Getting window handles");
		return super.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		System.out.println("Getting window handle");
		return super.getWindowHandle();
	}

	@Override
	public void close() {
		System.out.println("Closing the browser");
		super.close();
	}

	@Override
	public void quit() {
		super.quit();
		System.out.println("Quitting the browser");
	}

	// Additional methods for waiting, executing scripts, etc.
	public WebElement waitForElement(By by, int timeoutInSeconds) {
		System.out.println("Waiting for element by: " + by);
		WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void executeJavaScript(String script, Object... args) {
		System.out.println("Executing JavaScript: " + script);
		if (this instanceof JavascriptExecutor) {
			((JavascriptExecutor) this).executeScript(script, args);
		} else {
			throw new WebDriverException("Driver does not support JavaScript execution");
		}
	}

	public void takeScreenshot(String filePath) {
		System.out.println("Taking screenshot");
		if (this instanceof TakesScreenshot) {
			TakesScreenshot screenshotTaker = (TakesScreenshot) this;
			File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new WebDriverException("Driver does not support taking screenshots");
		}
	}
}
