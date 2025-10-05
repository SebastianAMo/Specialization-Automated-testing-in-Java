package org.automationexercise.core;

import org.automationexercise.core.driverfactory.DriverFactory;
import org.automationexercise.core.driverfactory.DriverFactoryProducer;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverInstance {
    private static final Logger logger = LoggerFactory.getLogger(DriverInstance.class);
    private static volatile DriverInstance instance;
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverInstance() {
        logger.debug("DriverInstance singleton created");
    }

    public static DriverInstance getInstance() {
        if (instance == null) {
            synchronized (DriverInstance.class) {
                if (instance == null) {
                    instance = new DriverInstance();
                }
            }
        }
        return instance;
    }

    public void initDriver(boolean headless, String browser) {
        initDriver(headless, browser, false, null);
    }

    public void initDriver(boolean headless, String browser, boolean remote, String gridUrl) {
        if (driver.get() == null) {
            logger.info("Starting driver initialization for browser: {} with headless: {} and remote: {}", browser, headless, remote);
            
            DriverFactory factory;
            if (remote && gridUrl != null) {
                // Create Chrome capabilities for remote execution
                org.openqa.selenium.chrome.ChromeOptions chromeOptions = new org.openqa.selenium.chrome.ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                }
                factory = DriverFactoryProducer.getFactory("remote", gridUrl, chromeOptions);
            } else {
                factory = DriverFactoryProducer.getFactory(browser);
            }
            
            driver.set(factory.createDriver(headless));
            driver.get().manage().window().maximize();

            logger.info("Driver initialization completed successfully");
        } else {
            logger.debug("Driver already initialized, skipping initialization");
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                logger.info("Driver quit successfully");
            } catch (Exception e) {
                logger.warn("Error during driver quit: {}", e.getMessage());
            } finally {
                driver.remove();
            }
        } else {
            logger.warn("No driver instance to quit");
        }
    }

    public boolean isDriverInitialized() {
        return driver.get() != null;
    }
}