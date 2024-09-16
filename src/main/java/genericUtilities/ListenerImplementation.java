package genericUtilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ListenerImplementation implements ITestListener { // extends TestListenerAdapter Both are
																				// same
	JavaUtility jUtility = new JavaUtility();
	SeleniumUtility sUtility = new SeleniumUtility();
	ExtentSparkReporter reporter;
	ExtentReports reports;
	ExtentTest test;
	File pathExternReport;

	@Override
	public void onStart(ITestContext context) {
		try {
			extentReportConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("****** Suite execution Statrted ******");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getName();
		System.out.println(methodName + " :****** Test execution Started ******");
		LoggerLoad.info("****** Test execution Started ******");
		// add @Test to the extent Report
		test = reports.createTest(methodName,
				"Look below for more information about the test method " + methodName + " Pass");
		test.assignAuthor("Aseem Ku Patel");
		test.assignCategory(result.getMethod().getGroups());
		// test.assignCategory(result.getTestClass().getName());
		test.assignDevice(JavaUtility.systemProperties("os.name"));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + ": ****** Test execution PASSED ******");
		LoggerLoad.info("Test Case - " + methodName + ":=> ****** Test Execution Passed ****** ");

		// log the PASS status message to the Extent Report
		// test.log(Status.PASS,methodName+" :****** Test execution PASSED ******");
		test.log(Status.PASS,
				MarkupHelper.createLabel(methodName + ": >>>> <b>execution PASSED</b>", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName(); // we can write result.getName()
		System.err.println(methodName + " : ****** Test execution FAILED ******");
        LoggerLoad.error("****** Test Execution Failed ******");

		// Log the Fail Status message to the Extent Report
		// test.log(Status.FAIL, methodName+": ****** Test execution FAILED ******");
		test.log(Status.FAIL, MarkupHelper.createLabel(methodName + ": execution FAILED", ExtentColor.RED));
		test.fail(result.getThrowable());

		String ScreenShotName = methodName + "-" + jUtility.getCurrentDate();

		try {
			String ScreenShotPath = sUtility.captureScreenShot(BaseClass.sDriver, ScreenShotName);
			// Attach the ScreenShot to the Extent Report
			test.addScreenCaptureFromPath(ScreenShotPath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + ": ****** Test execution Skipped ******");
		LoggerLoad.warn("Test Case - " + methodName + ":=> ****** Test Execution Skipped ******");

		// Log the Skip Status message to the Extent Report
		// test.log(Status.SKIP, methodName+": ****** Test execution Skipped ******");
		test.log(Status.SKIP, MarkupHelper.createLabel(methodName + ": execution Skipped", ExtentColor.YELLOW));
		test.skip(result.getThrowable());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + ": ****** Test execution Failed with TimeOut ******");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("****** Suite execution Finished ******");
		// Generate the Extent Report
		reports.flush();

		// Auto Launch the Extent Report
		try {
			pathExternReport = new File("ExternReports" + pathExternReport);
			Desktop.getDesktop().browse(pathExternReport.toURI());

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
		//sUtility.sendEmailReport(pathExternReport);
		System.out.println("****** Email Report Done ****");
		}
	}

	public void extentReportConfiguration() throws IOException {
		// Extent Report Configuration
		
		pathExternReport = new File("\\Report-" + jUtility.getCurrentDate() + ".html");
		reporter = new ExtentSparkReporter(".\\ExternReports" + pathExternReport);
		
		// Add System/environment information to the report
		reporter.loadJSONConfig(new File("./src/test/resources/extentReport-config.json"));
		/*
		 * reporter.config().setDocumentTitle("Extent Report Demo");
		 * reporter.config().setReportName("Vtiger Execution Report");
		 * reporter.config().setTheme(Theme.DARK);
		 * reporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
		 * reporter.config().setCss(".badge-primary{background-color:#ff0099}");
		 * reporter.config().setJs(
		 * "document.getElementsByClassName('logo')[0].style.display='none'");
		 */

		reports = new ExtentReports();
		reports.attachReporter(reporter);

		//reports.setSystemInfo("Browser Name", JavaUtility.getBrowserInfo(driver, "browserName"));
		//reports.setSystemInfo("Base OS", JavaUtility.getBrowserInfo(driver, "browserVersion"));

		
		reports.setSystemInfo("Base OS", JavaUtility.systemProperties("os.name"));
		reports.setSystemInfo("Java Version", JavaUtility.systemProperties("java.version"));
		reports.setSystemInfo("OS Architechture", JavaUtility.systemProperties("os.arch"));
		reports.setSystemInfo("User Name", JavaUtility.systemProperties("user.name"));
		reports.setSystemInfo("Reporter Country", JavaUtility.systemProperties("user.country"));

		reports.setSystemInfo("Base URL", "http://localhost:8888/index.php");
		reports.setSystemInfo("Execution Date and Time", jUtility.getCurrentDate());
		reports.setSystemInfo("Reporter Name", "Aseem Kumar Patel");
	}
}
