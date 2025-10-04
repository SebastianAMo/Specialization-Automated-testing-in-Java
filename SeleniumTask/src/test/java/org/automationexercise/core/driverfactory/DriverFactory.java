package org.automationexercise.core.driverfactory;

import org.openqa.selenium.WebDriver;

public interface DriverFactory {

    WebDriver createDriver(boolean headless);
}
