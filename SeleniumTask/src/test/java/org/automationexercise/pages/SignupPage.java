package org.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(SignupPage.class);

    // Exists two titles on the form
    private final By formTitle = By.cssSelector(".login-form .title");

    // Required fields
    private final By passwordInput = By.id("password");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By addressInput = By.id("address1");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipCodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");


    private final By signupButton = By.cssSelector("#form button[type='submit']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignupPageVisible() {
        return shortWait.until(ExpectedConditions.visibilityOfElementLocated(formTitle)).isDisplayed();
    }

    public SignupPage enterPassword(String password) {
        logger.debug("Entering password: {}", password);
        sendKeysToElementShortWait(passwordInput, password);
        return this;
    }
    public SignupPage enterFirstName(String firstName) {
        logger.debug("Entering first name: {}", firstName);
        sendKeysToElementShortWait(firstNameInput, firstName);
        return this;
    }
    public SignupPage enterLastName(String lastName) {
        logger.debug("Entering last name: {}", lastName);
        sendKeysToElementShortWait(lastNameInput, lastName);
        return this;
    }

    public SignupPage enterAddress(String address) {
        logger.debug("Entering address: {}", address);
        sendKeysToElementShortWait(addressInput, address);
        return this;
    }

    public SignupPage selectCountry(String country) {
        logger.debug("Selecting country: {}", country);
        getSelectElement(countryDropdown).selectByVisibleText(country);
        return this;
    }

    public SignupPage enterState(String state) {
        logger.debug("Entering state: {}", state);
        sendKeysToElementShortWait(stateInput, state);
        return this;
    }

    public SignupPage enterCity(String city) {
        logger.debug("Entering city: {}", city);
        sendKeysToElementShortWait(cityInput, city);
        return this;
    }

    public SignupPage enterZipCode(String zipCode) {
        logger.debug("Entering zip code: {}", zipCode);
        sendKeysToElementShortWait(zipCodeInput, zipCode);
        return this;
    }

    public SignupPage enterMobileNumber(String mobileNumber) {
        logger.debug("Entering mobile number: {}", mobileNumber);
        sendKeysToElementShortWait(mobileNumberInput, mobileNumber);
        return this;
    }

    public HomePage clickSignupButton() {
        logger.info("Clicking on Signup button to submit the form");
        WebElement button = driver.findElement(signupButton);
        scrollToElement(button);
        clickElementShortWait(button);
        return new HomePage(driver);
    }
    

}
