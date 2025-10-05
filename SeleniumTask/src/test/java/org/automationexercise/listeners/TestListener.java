package org.automationexercise.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.automationexercise.utils.ScreenshotUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.automationexercise.utils.ConfigLoader;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.log.SaveLogRQ;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

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


            // Attach screenshot to ReportPortal if available
            try {
                File file = new File(screenshotPath);
                if (file.exists()) {
                    byte[] content = Files.readAllBytes(file.toPath());
                    ReportPortal.emitLog(itemUuid -> {
                        SaveLogRQ rq = new SaveLogRQ();
                        rq.setLevel("ERROR");
                        rq.setMessage("Screenshot on failure: " + result.getName());
                        rq.setLogTime(new Date());
                        SaveLogRQ.File f = new SaveLogRQ.File();
                        f.setName(file.getName());
                        f.setContent(content);
                        rq.setFile(f);
                        return rq;
                    });
                }
            } catch (IOException e) {
                logger.warn("Failed to attach screenshot to ReportPortal: {}", e.getMessage());
            }
        }
    }

}