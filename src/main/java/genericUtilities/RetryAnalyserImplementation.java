package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementation implements IRetryAnalyzer
{
		int count=0;
		int retryLimit=2;
		
	@Override
	public boolean retry(ITestResult result) {
		while (count<retryLimit)
		{
			count++;
			return true;
		}
		return false;
	}
}
