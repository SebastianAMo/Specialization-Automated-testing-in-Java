package org.automationexercise.core;

import org.automationexercise.core.driverfactory.DriverFactory;
import org.automationexercise.core.driverfactory.DriverFactoryProducer;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
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
        if (driver.get() != null) {
            logger.debug("Driver already initialized, skipping initialization");
            return;
        }

        logger.info("Starting driver initialization for browser: {} with headless: {}", browser, headless);

        // Remote mode: -Dbrowser=remote [-DremoteBrowser=chrome] [-DgridUrl=http://...:4444]
        if ("remote".equalsIgnoreCase(browser)) {
            String remoteBrowser = System.getProperty("remoteBrowser", System.getenv().getOrDefault("REMOTE_BROWSER", "chrome"));
            String gridUrl = System.getProperty("gridUrl", System.getenv().getOrDefault("GRID_URL", "http://localhost:4444/wd/hub"));

            MutableCapabilities capabilities;
            if ("chrome".equalsIgnoreCase(remoteBrowser)) {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                }
                capabilities = options;
            } else {
                // Fallback empty capabilities for other browsers (extend as needed)
                capabilities = new MutableCapabilities();
            }

            DriverFactory factory = DriverFactoryProducer.getFactory("remote", gridUrl, capabilities);
            driver.set(factory.createDriver(headless));
        } else {
            DriverFactory factory = DriverFactoryProducer.getFactory(browser);
            driver.set(factory.createDriver(headless));
        }

        driver.get().manage().window().maximize();
        logger.info("Driver initialization completed successfully");
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