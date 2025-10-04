package org.automationexercise.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private  static final Logger logger = LoggerFactory.getLogger(LoginPage.class);


    private final By loginEmailInput = By.cssSelector("form[action='/login'] input[type='email']");
    private final By loginPasswordInput = By.cssSelector("form[action='/login'] input[type='password']");
    private final By loginButton = By.cssSelector("form[action='/login'] button"); // form[action='/login'] button[type='submit']

    private final By errorMessage = By.cssSelector("p[style='color: red;']");


    private final By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("button[data-qa='signup-button']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageVisible() {
        return Objects.requireNonNull(driver.getCurrentUrl()).endsWith("login");
    }

    public LoginPage enterLoginEmail(String email) {
        logger.info("Entering login email: {}", email);
        sendKeysToElementShortWait(loginEmailInput, email);
        return this;
    }
    
    public LoginPage enterLoginPassword(String password) {
        logger.info("Entering login password: {}", password);
        sendKeysToElementShortWait(loginPasswordInput, password);
        return this;
    }
    
    public LoginPage clickLoginButton() {
        logger.info("Clicking on Login button");
        clickElementShortWait(loginButton);
        return this;
    }

    public String getErrorMessage() {
        logger.info("Retrieving error message text");
        return driver.findElement(errorMessage).getText();
    }

    public LoginPage enterSignupName(String name) {
        logger.info("Entering signup name: {}", name);
        sendKeysToElementShortWait(signupNameInput, name);
        return this;
    }

    public LoginPage enterSignupEmail(String email) {
        logger.info("Entering signup email: {}", email);
        sendKeysToElementShortWait(signupEmailInput, email);
        return this;
    }

    public SignupPage clickSignupButton() {
        logger.info("Clicking on Signup button");
        clickElementShortWait(signupButton);
        return new SignupPage(driver);
    }

    
}
