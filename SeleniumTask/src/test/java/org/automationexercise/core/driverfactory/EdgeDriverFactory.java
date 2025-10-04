package org.automationexercise.core.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdgeDriverFactory implements DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(EdgeDriverFactory.class);

    @Override
    public WebDriver createDriver(boolean headless) {
        var Options = new EdgeOptions();
        if (headless) {
            Options.addArguments("--headless");
            Options.addArguments("--disable-gpu");
            logger.debug("Added headless arguments for Edge");
        }
        WebDriverManager.edgedriver().setup();
        logger.info("Edge driver initialized successfully");
        return new EdgeDriver(Options);
    }
}
