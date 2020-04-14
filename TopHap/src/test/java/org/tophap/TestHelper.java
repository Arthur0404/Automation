package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.util.List;

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

    public static final String CREDIT_CARD_4LAST_DIGITS = "1117";
    public static String CREDIT_CARD = "601111111111" + CREDIT_CARD_4LAST_DIGITS;
    public static final String CREDIT_CARD_EXPIRATION = "12/50";
    public static final String CREDIT_CARD_CVV = "111";
    public static final String CREDIT_CARD_PASSWORD = "111111";

    public static void enterCreditCardData(WebDriver driver, String card_number, String card_exp, String card_cvv, String card_pass) {
        driver.findElement(By.xpath("//*[@name='cardnumber']")).sendKeys(card_number);
        driver.findElement(By.xpath("//*[@name='exp-date']")).sendKeys(card_exp);
        driver.findElement(By.xpath("//*[@name='cvc']")).sendKeys(card_cvv);
        driver.findElement(By.xpath("//*[@name='postal']")).sendKeys(card_pass);
    }

    public static final String EMAIL = "0.7752235088120072+ta@example.com";
    public static final String PASS = "111111";

    public static void signIn(WebDriver driver, String email, String pass) {
        driver.findElement(By.xpath("//a[@class='th-link ml-3']")).click();
        driver.findElement(By.xpath("//input[@placeholder='E-mail']")).sendKeys(EMAIL);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(PASS);
        driver.findElement(By.xpath("//*[@class='MuiButtonBase-root th-button']")).click();
    }

    public static void profileDropMenu(WebDriver driver) {
        driver.findElement(By.xpath("//div[@class='jsx-3275066862 th-menu-item th-user-avatar']")).click();
        driver.findElement(By.linkText("Account")).click();
    }

    public static void billingAccountManager(WebDriver driver) {
        driver.findElement(By.linkText("Billing")).click();
    }
}
