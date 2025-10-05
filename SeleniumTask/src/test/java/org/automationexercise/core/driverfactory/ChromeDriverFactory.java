package org.automationexercise.core.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeDriverFactory implements DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(ChromeDriverFactory.class);

    @Override
    public WebDriver createDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
            logger.debug("Added headless arguments for Chrome");
        }
        WebDriverManager.chromedriver().setup();
        logger.info("Chrome driver initialized successfully");
        return new ChromeDriver(options);
    }
}