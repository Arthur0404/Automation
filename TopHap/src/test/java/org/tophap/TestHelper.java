package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHelper {

    public static void closeWelcome(WebDriver drive) {
        List<WebElement> frames = drive.findElements(By.xpath("//iframe[@title='Intercom Live Chat']"));
        if (frames.size() > 0) {
            drive.switchTo().frame(frames.get(0));
            drive.findElement(By.xpath("//span[@aria-label='Close']")).click();
            drive.switchTo().defaultContent();
        }
    }

    public static ExpectedCondition<WebElement> movingIsFinished(final By locator) {
        return new ExpectedCondition<WebElement>() {

            private WebElement element = null;
            private Point location = null;

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

            @Override
            public String toString() {
                return "moving of element is finished, located by: " + locator;
            }
        };
    }

    public static void moveToElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public static void moveToElement(WebDriver driver, By locator) {
        moveToElement(driver, driver.findElement(locator));
    }

    public static void moveToHiddenElement(WebDriver driver, By locator, By locatorDropDown) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement button = driver.findElement(locator);
        WebElement buttonDropDown = driver.findElement(locatorDropDown);
        if (driver.findElement(locator).isDisplayed()) {
            action.moveToElement(button).perform();
        } else {
            action.moveToElement(buttonDropDown).perform();
            moveToElement(driver,
                    new WebDriverWait(driver, 10).until(movingIsFinished(locator)));
        }
    }

    private static class Key {
        int code;
        boolean shift;

        public Key(int code, boolean shift) {
            this.code = code;
            this.shift = shift;
        }
    }

    private static Map<Character, Key> characterMap;
    static {
         characterMap = new HashMap<>();
         characterMap.put(':', new Key(KeyEvent.VK_SEMICOLON, true));
         characterMap.put('_', new Key(KeyEvent.VK_MINUS, true));
    }

    public static void sendKeys(String str) {
        try {
            Robot robot = new Robot();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                int code = ch;
                boolean shift = false;

                if (Character.isAlphabetic(ch)) {
                    code = KeyEvent.getExtendedKeyCodeForChar(ch);
                    shift = Character.isUpperCase(ch);
                } else {
                    Key key = characterMap.get(ch);
                    if (key != null) {
                        code = key.code;
                        shift = key.shift;
                    }
                }

                if (shift) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }

                robot.keyPress(code);
                robot.keyRelease(code);

                if (shift) {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}