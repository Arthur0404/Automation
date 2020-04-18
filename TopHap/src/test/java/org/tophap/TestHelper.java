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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHelper {

    public static void loginTheSite(WebDriver driver) {
        driver.get("https://next.tophap.com/");
        driver.findElement(By.xpath("//button[text()='Try for Free']")).click();

        closeWelcome(driver);
    }

    public static void selectPricing(WebDriver driver) {
        driver.findElement(By.linkText("Pricing")).click();
    }

    public static void closeWelcome(WebDriver drive) {
        List<WebElement> frames = drive.findElements(By.xpath("//iframe[@title='Intercom Live Chat']"));
        if (frames.size() > 0) {
            drive.switchTo().frame(frames.get(0));
            drive.findElement(By.xpath("//span[@aria-label='Close']")).click();
            drive.switchTo().defaultContent();
        }
    }

   public static final String CREDIT_CARD_4LAST_DIGITS = "4242";
    public static String CREDIT_CARD = "424242424242"+CREDIT_CARD_4LAST_DIGITS;
    public static final String CREDIT_CARD_EXPIRATION = "12/50";
    public static final String CREDIT_CARD_CVV = "111";
    public static final String ZIP_CODE = "111111";

    public static void enterCreditCardData(WebDriver driver, String card_number, String card_exp, String card_cvv, String card_pass) {
        driver.findElement(By.xpath("//*[@name='cardnumber']")).sendKeys(card_number);
        driver.findElement(By.xpath("//*[@name='exp-date']")).sendKeys(card_exp);
        driver.findElement(By.xpath("//*[@name='cvc']")).sendKeys(card_cvv);
        driver.findElement(By.xpath("//*[@name='postal']")).sendKeys(card_pass);
    }

    public static void signIn(WebDriver driver, String email, String pass) {
        driver.findElement(By.xpath("//a[@class='th-link ml-3']")).click();
        driver.findElement(By.xpath("//input[@placeholder='E-mail']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pass);
        driver.findElement(By.xpath("//*[@class='MuiButtonBase-root th-button']")).click();
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

    public static void openUserProfile(WebDriver driver) {
        TestHelper.moveToElement(driver, By.className("UserAvatar--inner"));
        driver.findElement(By.linkText("Account")).click();
    }

    public static void emailConfirmationFailureMsgClose(WebDriver driver){
            WebElement waitForSignUpWindow = new WebDriverWait(driver, 10).until(TestHelper.movingIsFinished(
                    By.xpath("//div[@class='Toastify__toast-container Toastify__toast-container--top-right th-notification-container']")));
            driver.findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-close-button']")).click();

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
}