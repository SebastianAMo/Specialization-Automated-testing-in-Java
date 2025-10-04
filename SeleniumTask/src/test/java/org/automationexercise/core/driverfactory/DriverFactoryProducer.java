package org.automationexercise.core.driverfactory;


import org.openqa.selenium.MutableCapabilities;


public class DriverFactoryProducer {
    public static DriverFactory getFactory(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriverFactory();
            case "firefox" -> new FirefoxDriverFactory();
            case "edge" -> new EdgeDriverFactory();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    public static DriverFactory getFactory(String browser, String gridUrl, MutableCapabilities capabilities) {
        if (browser.equalsIgnoreCase("remote")) {
            return new RemoteDriverFactory(gridUrl, capabilities);
        }
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriverFactory();
            case "firefox" -> new FirefoxDriverFactory();
            case "edge" -> new EdgeDriverFactory();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}