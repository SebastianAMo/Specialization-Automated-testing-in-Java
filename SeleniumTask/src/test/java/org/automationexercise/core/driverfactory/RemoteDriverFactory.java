package org.automationexercise.core.driverfactory;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverFactory implements DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(RemoteDriverFactory.class);

    private final String gridUrl;
    private final MutableCapabilities capabilities;

    public RemoteDriverFactory(String gridUrl, MutableCapabilities capabilities) {
        this.gridUrl = gridUrl;
        this.capabilities = capabilities;
    }

    @Override
    public WebDriver createDriver(boolean headless) {
        try {
            return new RemoteWebDriver(new URL(gridUrl), capabilities);
        } catch (MalformedURLException e) {
            logger.error("Invalid Grid URL: " + gridUrl, e);
            throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
        }

    }
}
