package org.automationexercise.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;



public class AccountDeletePage extends BasePage {

    private final By accountDeletedMessage = By.xpath("//b[text()='Account Deleted!']");

    public AccountDeletePage(WebDriver driver) {
        super(driver);
    }

    public AccountDeletePage goToHomePage() {
        driver.get("https://automationexercise.com/");
        return this;
    }

    public boolean isDeleteMessageVisible() {
        return driver.findElement(accountDeletedMessage).isDisplayed();
    }
}
