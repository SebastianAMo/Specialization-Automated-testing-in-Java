package org.automationexercise.pages.components;

import org.automationexercise.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.automationexercise.decorators.LoggingHighlightWebElement;

public class NavigationBar {

    private static final Logger logger = LoggerFactory.getLogger(NavigationBar.class);
    private final WebDriver driver;
    private final WebDriverWait shortWait; 


    private final By homeLink = By.xpath("//a[@href='/']");
    private final By signupLoginLink = By.xpath("//a[@href='/login']");
    private final By cartLink = By.xpath("//a[@href='/view_cart']");
    private final By deleteAccountLink = By.xpath("//a[@href='/delete_account']");
    
    
    public NavigationBar(WebDriver driver, WebDriverWait shortWait) {
        this.driver = driver;
        this.shortWait = shortWait;
    }
    
    public HomePage clickHome() {
        logger.info("Clicking on Home link in navigation bar");
        new LoggingHighlightWebElement(shortWait.until(ExpectedConditions.elementToBeClickable(homeLink)), driver).click();
        return new HomePage(driver);
    }
    
    public LoginPage clickSignupLogin() {
        logger.info("Clicking on Signup/Login link in navigation bar");
        new LoggingHighlightWebElement(shortWait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)), driver).click();
        return new LoginPage(driver);
    }
    
    public CartPage clickCart() {
        logger.info("Clicking on Cart link in navigation bar");
        new LoggingHighlightWebElement(shortWait.until(ExpectedConditions.elementToBeClickable(cartLink)), driver).click();
        return new CartPage(driver);
    }

    public AccountDeletePage clickDeleteAccount() {
        logger.info("Clicking on Delete Account link in navigation bar");
        new LoggingHighlightWebElement(shortWait.until(ExpectedConditions.elementToBeClickable(deleteAccountLink)), driver).click();
        return new AccountDeletePage(driver);
    }

    public boolean isNavigationOptionSelected(String option) {
        logger.info("Checking if navigation option '{}' is selected", option);
        By optionLocator = By.xpath("//ul[contains(@class,'navbar-nav')]//a[@href='/" + option.toLowerCase() + "']");
        WebElement optionElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        String cssColor = optionElement.getCssValue("color");
        if (cssColor.equals("rgba(255, 165, 0, 1)")) {// Orange colo
            return true;
        }
        logger.warn("Navigation option '{}' is not selected (color: {})", option, cssColor);
        return false;
    }
    
}
