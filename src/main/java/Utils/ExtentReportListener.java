package Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.testng.Reporter;
import org.testng.internal.IResultListener;

public class ExtentReportListener implements ITestListener, IResultListener {
        protected static ExtentReports reports;
        protected static ExtentTest test;

        public ExtentHtmlReporter htmlReporter;

        private static String resultpath = getResultPath();


        public static void deleteDirectory(File directory) {
            if (directory.exists()) {
                File[] files = directory.listFiles();
                if (null != files) {
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(files[i].getName());
                        if (files[i].isDirectory()) {
                            deleteDirectory(files[i]);
                        } else {
                            files[i].delete();
                        }
                    }
                }
            }
        }

        private static String getResultPath() {

            resultpath = "test";//new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
            if (!new File(resultpath).isDirectory()) {
                new File(resultpath);
            }
            return resultpath;
        }

        public void onTestStart(ITestResult result) {

            test = reports.createTest(result.getMethod().getMethodName());
//            System.out.println(result.getTestClass().getTestName());
            System.out.println(result.getMethod().getMethodName());
        }

        public void onTestSuccess(ITestResult result) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
        }

        public void onTestFailure(ITestResult result) {
            test.log(Status.FAIL, result.getThrowable());
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
        }

        public void onTestSkipped(ITestResult result) {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.BLACK));
        }

        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
            // TODO Auto-generated method stub
            test = reports.createTest(result.getName());
            test.log(Status.INFO,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
        }

        public void reportLogs(String message) {
            test.log(Status.INFO, message); // For extendTest HTML Report
            test.info("Message" + message);
            Reporter.log(message);
        }

        public void onStart(ITestContext context) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
            String repName="Test-Report-"+timeStamp+".html";

            htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
            htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");

            reports=new ExtentReports();
            reports.setSystemInfo("Host Name", "Jarvis");
            reports.setSystemInfo("Environment", "Staging");
            reports.setSystemInfo("User Name", "Barathwaj Ravisankar");
            reports.setSystemInfo("Designation", "Senior SDET");
            reports.setSystemInfo("Organization", "Boeing");
            reports.setSystemInfo("Framework Type", "Rest Assured API Automation Framework");
            reports.attachReporter(htmlReporter);

            htmlReporter.config().setDocumentTitle("Test Project");
            htmlReporter.config().setReportName("Rest Assured API Test Automation Report");
            htmlReporter.config().setTheme(Theme.DARK);

        }

        public void onFinish(ITestContext context) {
            reports.flush();

        }

    }
