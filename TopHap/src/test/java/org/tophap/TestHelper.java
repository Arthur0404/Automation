package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TestHelper {

    private static class MovingExpectedCondition implements ExpectedCondition<WebElement> {

        private By locator;
        private WebElement element = null;
        private Point location = null;

        public MovingExpectedCondition(WebElement element) {
            this.element = element;
        }

        public MovingExpectedCondition(By locator) {
            this.locator = locator;
        }

        @Override
        public WebElement apply(WebDriver driver) {
            if (element == null) {
                try {
                    element = driver.findElement(locator);
                } catch (NoSuchElementException e) {
                    return null;
                }
            }

            if (element.isDisplayed()) {
                Point location = element.getLocation();
                if (location.equals(this.location)) {
                    return element;
                }
                this.location = location;
            }

            return null;
        }
    }

    public static ExpectedCondition<WebElement> movingIsFinished(final By locator) {
        return new MovingExpectedCondition(locator);
    }

    public static ExpectedCondition<WebElement> movingIsFinished(final WebElement element) {
        return new MovingExpectedCondition(element);
    }
}