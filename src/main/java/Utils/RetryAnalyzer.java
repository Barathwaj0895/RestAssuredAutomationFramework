package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * This class has been created by Barathwaj Ravisankar on May 12, 2022
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    int retryCount = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        while (count < retryCount) {
            count++;
            return true;
        }
        return false;
    }
}
