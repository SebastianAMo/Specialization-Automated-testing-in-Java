package org.automationexercise.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.automationexercise.utils.ScreenshotUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.automationexercise.utils.ConfigLoader;

public class TestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    private static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ConfigLoader configLoader = ConfigLoader.getInstance();
        if (driver != null && configLoader.getBooleanProperty("screenshot.on.failure", true)) {
            logger.error("Test '{}' failed. Capturing screenshot...", result.getName());
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName(), configLoader.getProperty("screenshot.path"));
            System.out.println("❌ Test failed: " + result.getName());
            System.out.println("📸 Screenshot saved at: " + screenshotPath);
        }
    }

}