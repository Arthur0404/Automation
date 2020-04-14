package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHelper {

    public static final String NAME = "TopHap";
    public static final String PASSWORD = "TopHap";

    public static void loginTheSite(WebDriver driver) {
        driver.get("https://next.tophap.com/");
        driver.findElement(By.xpath("//button[text()='Try for Free']")).click();

        closeWelcome(driver);
    }

    public static void signUp(WebDriver driver, String name, String email, String pass) throws InterruptedException {
        // open Sign up form from the Home page
        driver.findElement(By.className("th-signup-button")).click();
        // verify you are on the Sign up form
        assertEquals("Sign Up for Free", driver.findElement(By.xpath("//div[@role='dialog']//h1")).getText());
        // populate Sign up form and Submit
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pass);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public static void closeWelcome(WebDriver drive) {
        List<WebElement> frames = drive.findElements(By.xpath("//iframe[@title='Intercom Live Chat']"));
        if (frames.size() > 0) {
            drive.switchTo().frame(frames.get(0));
            drive.findElement(By.xpath("//span[@aria-label='Close']")).click();
            drive.switchTo().defaultContent();
        }
    }

    public static void moveToElement(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        WebElement button = driver.findElement(locator);
        action.moveToElement(button).perform();
    }
}
