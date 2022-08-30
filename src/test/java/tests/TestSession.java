package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.base.Throwables;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


import framework.TestSessionInitiator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * This class is to start the test session by calling WebDriver to initiate
 */
public class TestSession {

    private final String APP_URL = ".Url";
    private final String APP_ENV = "env";
    private final String ENV_CONFIGURATION = "config.properties";
    private final String APP_PLATFORM = "test.platform";
    private Properties properties;

    public static ExtentTest extentTest;
    protected static ExtentReports reports;
    protected static ExtentSparkReporter sparkReporter;

    protected TestSessionInitiator testSession;
    public static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        testSession = new TestSessionInitiator();

        driver = testSession.getWebDriver();

        if (System.getProperty(APP_PLATFORM).equals("desktop")
                || System.getProperty(APP_PLATFORM).equals("androidbrowser")
                || System.getProperty(APP_PLATFORM).equals("iOSbrowser")) {

            launchApplication();
        }

    }


    public void launchApplication() {

        driver.get(getAppURL());

    }

    private String getAppURL() {

        String url;
        properties = getTestResourceProperties(ENV_CONFIGURATION);

        url = System.getProperty(APP_ENV) != null ? properties.getProperty(System.getProperty(APP_ENV).concat(APP_URL))
                : properties.getProperty("qa.Url");

        return url;
    }

    private Properties getTestResourceProperties(String propFileName) {
        properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;

    }

    public void launchApplication(String url) {

    }

    @SuppressWarnings("unchecked")
    public <T> T initPlatformPage(T pageInterface, String pageClass) throws Exception {

        switch (testSession.getConfigRegistry().getTestPlatform()) {

            case androidapp:
            case iOSapp:
                pageInterface = (T) Class.forName("pageactions.mobileapp." + pageClass)
                        .getDeclaredConstructor(WebDriver.class).newInstance(driver);
                break;
        }

        return pageInterface;
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        System.out.println("********************************************************************");
        System.out.println("RUNNING TESTS ON " + this.getClass());
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestMethod(Method method) {
        try {
            String className = method.getDeclaringClass().getName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.out.println("Running Test: " + className + "." + method.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        extentTest = reports.createTest(method.getName());

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method, ITestResult testResult, ITestContext testContext) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE || testResult.getStatus() == ITestResult.SKIP
                || testResult.getStatus() == ITestResult.STARTED) {
            String className = method.getDeclaringClass().getName();
            className = className.substring(className.lastIndexOf('.') + 1);
            String suiteName = testContext.getCurrentXmlTest().getSuite().getName();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotName = "target/screenshots/" + suiteName + "_" + className + "_" + method.getName()
                    + ".png";
            System.out.println(
                    String.format("Failure in Suite=%s Class=%s Method=%s", suiteName, className, method.getName()));
            FileUtils.copyFile(srcFile, new File(screenshotName));
            for (String log : Reporter.getOutput()) {
                extentTest.log(Status.PASS, log);
            }
            if (testResult.getStatus() == ITestResult.STARTED) {
                extentTest.log(Status.FAIL,
                        "The failed setups: \n" + testContext.getFailedConfigurations().getAllMethods().toString(),
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(System.getProperty("user.dir") + "/" + screenshotName)
                                .build());
            } else {
                extentTest.log(Status.FAIL, Throwables.getStackTraceAsString(testResult.getThrowable()),
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(System.getProperty("user.dir") + "/" + screenshotName)
                                .build());
            }
        } else {
            for (String log : Reporter.getOutput()) {
                extentTest.log(Status.PASS, log);
            }
        }
        Reporter.clear();

    }

    @AfterClass(alwaysRun = true)
    public void cleanSetup() {
        testSession.quitDriverSession();

    }

    @BeforeSuite
    public void suiteSetup() {
        reports = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("target/report/automation-report.html");
        reports.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void suiteTearDown() {
        reports.flush();
    }

}