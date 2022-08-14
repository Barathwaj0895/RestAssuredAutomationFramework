package runner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

/**
 * This class has been created by Barathwaj Ravisankar on August 01, 2022
 */

// This class is deprecated
@Deprecated
public class BaseTest {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Sr.SDET", "Barathwaj Ravisankar");
    }


    @BeforeMethod
    public void beforeMethodMethod(Method testMethod) {
        logger = extentReports.createTest(testMethod.getName());
    }


    @AfterMethod
    public void afterMethodMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case:" + methodName + "Passed";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, markup);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case:" + methodName + "Failed";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, result.getThrowable());
            logger.log(Status.FAIL, markup);
        } else if (result.getStatus() == ITestResult.SKIP) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case:" + methodName + "Skipped";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.BLACK);
            logger.log(Status.SKIP, result.getInstanceName());
            logger.log(Status.SKIP, markup);
        }
    }


    @AfterTest
    public void afterTestMethod() {
        extentReports.flush();
    }

}