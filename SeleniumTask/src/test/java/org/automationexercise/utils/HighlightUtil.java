package org.automationexercise.utils;

import org.openqa.selenium.*;

public class HighlightUtil {


    private WebDriver driver;

    public HighlightUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void highlight(WebElement element) {
        try {
            if (element != null && isReady(element)) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');",
                        element
                );
            }
        } catch (Exception e) {
            System.err.println("Highlight failed: " + e.getMessage());
        }

    }

    public void unhighlight(WebElement element) {
        if (element != null && isReady(element)) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].setAttribute('style', '');",
                        element
                );
            } catch (StaleElementReferenceException e) {
                System.err.println("Unhighlight failed: " + e.getMessage());
                throw e;
            }
        }
    }

    private boolean isReady(WebElement element) {
        try {
            return element != null && element.isDisplayed() && element.isEnabled();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }
}

