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
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener{
	JavaUtility jUtility = new JavaUtility();
	ExtentSparkReporter reporter;
	ExtentReports reports;
	ExtentTest test;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("****** Suite execution Statrted ******");
		extentReportConfiguration();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+" :****** Test execution Statrted ******");

		//add @Test to the extent Report
		test = reports.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+": ****** Test execution PASSED ******");

		// log the PASS status message to the Extent Report
		//test.log(Status.PASS,methodName+" :****** Test execution PASSED ******");
		test.log(Status.PASS, MarkupHelper.createLabel(methodName+": execution PASSED",ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+": ****** Test execution FAILED ******");

		// Log the Fail Status message to the Extent Report
		//test.log(Status.FAIL, methodName+": ****** Test execution FAILED ******");
		test.log(Status.FAIL, MarkupHelper.createLabel(methodName+": execution FAILED",ExtentColor.RED));
		test.fail(result.getThrowable());

		SeleniumUtility sUtility = new SeleniumUtility();
		String ScreenShotName = methodName+"-"+ jUtility.getCurrentDate();

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
		System.out.println(methodName+": ****** Test execution Skipped ******");

		// Log the Skip Status message to the Extent Report
		//test.log(Status.SKIP, methodName+": ****** Test execution Skipped ******");
		test.log(Status.SKIP, MarkupHelper.createLabel(methodName+": execution Skipped",ExtentColor.YELLOW));
		test.skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+": ****** Test execution Failed with TimeOut ******");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("****** Suite execution Finished ******");
		//Generate the Extent Report
		reports.flush();
		
		//Auto Launch the Extern Report
		/*
		 * String pathExternReport =
		 * System.getProperty("user.dir")+"\\ExternReports\\Report-"+
		 * jUtility.getCurrentDate()+".html"; File externReportFile = new
		 * File(pathExternReport); try {
		 * Desktop.getDesktop().browse(externReportFile.toURI()); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */
	}

	public void extentReportConfiguration()
	{
		//Extent Report Configuration
		// Add System/environment information to the report
		reporter = new ExtentSparkReporter(".\\ExternReports\\Report-"+ jUtility.getCurrentDate()+".html");
		reporter.config().setDocumentTitle("Extent Report Demo");
		reporter.config().setReportName("Vtiger Execution Report");
		reporter.config().setTheme(Theme.DARK);

		reports = new ExtentReports();
		reports.attachReporter(reporter);

		//Add Configuration information to change the look and feel of the report
		reports.setSystemInfo("Base OS", JavaUtility.systemProperties("os.name"));
		reports.setSystemInfo("Base Browser", "Chrome");
		reports.setSystemInfo("Base URL", "http://localhost:8888/index.php");
		reports.setSystemInfo("Execution Date and Time", jUtility.getCurrentDate());
		reports.setSystemInfo("Java Version", JavaUtility.systemProperties("java.version"));
		reports.setSystemInfo("OS Architechture", JavaUtility.systemProperties("os.arch"));
		reports.setSystemInfo("Reporter Name", "Aseem Kumar Patel");
		reports.setSystemInfo("User Name", "user.name");
		reports.setSystemInfo("Reporter Country", "user.country");
	}

}
