package org.automationexercise.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.automationexercise.utils.ScreenshotUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.automationexercise.utils.ConfigLoader;
import java.util.Base64;

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
            
            // Take screenshot and get both path and binary data
            ScreenshotUtil.ScreenshotResult screenshotResult = ScreenshotUtil.takeScreenshotWithBinary(
                driver, result.getName(), configLoader.getProperty("screenshot.path"));
            
            System.out.println("❌ Test failed: " + result.getName());
            System.out.println("📸 Screenshot saved at: " + screenshotResult.getFilePath());

            // Attach screenshot to ReportPortal using logger approach
            if (screenshotResult.getBinaryData() != null) {
                String base64Screenshot = Base64.getEncoder().encodeToString(screenshotResult.getBinaryData());
                logger.info("RP_MESSAGE#BASE64#{}#Screenshot on failure: {}", 
                    base64Screenshot, result.getName());
            }
        }
    }

}