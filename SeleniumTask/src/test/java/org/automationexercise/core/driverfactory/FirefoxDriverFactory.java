package org.automationexercise.core.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirefoxDriverFactory implements DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(FirefoxDriverFactory.class);

    @Override
    public WebDriver createDriver(boolean headless) {
        var Options = new FirefoxOptions();
        if (headless) {
            Options.addArguments("--headless");
            Options.addArguments("--disable-gpu");
            logger.debug("Added headless arguments for Firefox");
        }

        WebDriverManager.firefoxdriver().setup();
        logger.info("Firefox driver initialized successfully");
        return new FirefoxDriver(Options);
    }
}