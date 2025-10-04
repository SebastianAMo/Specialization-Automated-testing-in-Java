package org.automationexercise.pages;

import org.automationexercise.decorators.LoggingHighlightWebElement;
import org.automationexercise.pages.components.NavigationBar;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;



public abstract class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected WebDriver driver;
    protected WebDriverWait shortWait;
    protected WebDriverWait longWait;
    protected Actions actions;
    protected JavascriptExecutor js;

    protected static final int IMPLICIT_TIMEOUT = 2;

    protected static final int LONG_TIMEOUT = 30;
    protected static final int SHORT_TIMEOUT = 5;

    public NavigationBar navigationBar;

    public BasePage(WebDriver baseDriver) {
        if (baseDriver == null) {
            throw new IllegalArgumentException("Driver must not be null");
        }
        this.driver = baseDriver;

        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        this.actions = new Actions(driver); 
        this.js = (JavascriptExecutor) driver;
        this.navigationBar = new NavigationBar(driver, shortWait);

        // Set an implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_TIMEOUT));
    }

    public void scrollToElement(WebElement element) {
        logger.info("Scrolling to element: {}", element);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public Select getSelectElement(By locator) {
        WebElement element =  shortWait.until(ExpectedConditions.elementToBeClickable(locator));
        return new Select(element);
    }


    public void clickElementShortWait(WebElement element) {
        WebElement clickableElement = new LoggingHighlightWebElement(shortWait.until(ExpectedConditions.elementToBeClickable(element)), driver);
        clickableElement.click();
    }

    public void clickElementShortWait(By locator) {
        WebElement element = driver.findElement(locator);
        new LoggingHighlightWebElement(shortWait.until(ExpectedConditions.elementToBeClickable(element)), driver).click();
    }

    public void sendKeysToElementShortWait(By locator, String text) {
        WebElement element = new LoggingHighlightWebElement(shortWait.until(ExpectedConditions.elementToBeClickable(locator)), driver);
        element.clear();
        element.sendKeys(text);
    }
}
