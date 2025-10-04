package org.automationexercise.decorators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.automationexercise.utils.HighlightUtil;

public class LoggingHighlightWebElement extends WebElementDecorator {
    private static final Logger logger = LoggerFactory.getLogger(LoggingHighlightWebElement.class);
    private final HighlightUtil highlightUtil;

    public LoggingHighlightWebElement(WebElement element, WebDriver driver) {
        super(element);
        this.highlightUtil = new HighlightUtil(driver);
    }

    @Override
    public void click() {
        highlightUtil.highlight( element);
        logger.debug("Clicking on element: {}", element);
        super.click();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        highlightUtil.highlight(element);
        logger.debug("Sending keys to element: {} | Keys: {}", element, String.join("", keysToSend));
        super.sendKeys(keysToSend);
    }
}
