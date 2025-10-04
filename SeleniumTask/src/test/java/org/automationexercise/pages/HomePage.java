package org.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    private final By featuresItems = By.xpath("//div[@class='features_items'] //div[contains(@class,'single-products')]");
    private final By closedSuccessAddedToCartModalButton = By.cssSelector(".close-modal");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageVisible() {
        boolean isHomeOptionsSelected = navigationBar.isNavigationOptionSelected("");
        return isHomeOptionsSelected && Objects.equals(driver.getTitle(), "Automation Exercise");
    }

    // Return list of WebElements representing "Add to cart" buttons under "FEATURES ITEMS" section
    private List<WebElement> getFeaturesItems() {
        logger.info("Retrieving list of feature items on the home page");
        return longWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(featuresItems));
    }

    public HomePage addRandomProductToCart() {
        logger.info("Adding a random product to the cart from the FEATURES ITEMS section");
        List<WebElement> items = getFeaturesItems();
        logger.debug("Found {} items in the FEATURES ITEMS section", items.size());
        if (items.isEmpty()) {
            throw new IllegalStateException("No items found in the FEATURES ITEMS section");
        }
        
        WebElement randomItem = items.get(new Random().nextInt(items.size()));
        scrollToElement(randomItem);
        WebElement addToCartButton = randomItem.findElement(By.xpath(".//a[contains(@class,'add-to-cart')]"));
        
        clickElementShortWait(addToCartButton);
        clickElementShortWait(closedSuccessAddedToCartModalButton);

        return this;
    }
}
