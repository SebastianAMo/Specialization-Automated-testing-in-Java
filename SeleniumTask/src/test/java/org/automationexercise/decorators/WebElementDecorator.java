package org.automationexercise.decorators;

import org.openqa.selenium.WebElement;

public abstract class WebElementDecorator implements WebElement {
    protected WebElement element;

    public WebElementDecorator(WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public java.util.List<WebElement> findElements(org.openqa.selenium.By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(org.openqa.selenium.By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public org.openqa.selenium.Point getLocation() {
        return element.getLocation();
    }

    @Override
    public org.openqa.selenium.Dimension getSize() {
        return element.getSize();
    }

    @Override
    public org.openqa.selenium.Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(org.openqa.selenium.OutputType<X> target) throws org.openqa.selenium.WebDriverException {
        return element.getScreenshotAs(target);
    }
}
