package org.automationexercise.tests;

import org.automationexercise.core.DriverInstance;
import org.automationexercise.listeners.TestListener;
import org.automationexercise.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.testng.AllureTestNg;


@Listeners({TestListener.class, AllureTestNg.class})
public abstract class BaseTest {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "headless", "environment"})
    public void setUp(@Optional("chrome") String browser, @Optional("true") String headless, @Optional("dev") String environment) {
        // Set environment before initializing driver
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.setEnvironment(environment);
        
        boolean isHeadless = Boolean.parseBoolean(headless);
        
        DriverInstance driverInstance = DriverInstance.getInstance();
        driverInstance.initDriver(isHeadless, browser);
        driver = driverInstance.getDriver();

        driver.get(configLoader.getProperty("base.url"));

        TestListener.setDriver(driver);
        
        logger.info("Test setup completed successfully for environment: {}", environment);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverInstance driverInstance = DriverInstance.getInstance();
        driverInstance.quitDriver();
        logger.info("Test teardown completed");
    }
}
