package org.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CartPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    private final By emptyCartButton = By.xpath("//a[text()='Empty Cart-']");
    

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageVisible() {
        return navigationBar.isNavigationOptionSelected("view_cart");
    }

    public CartPage clickEmptyCartButton() {
        logger.info("Clicking on Empty Cart button");
        clickElementShortWait(emptyCartButton);
        return this;
    }



}
