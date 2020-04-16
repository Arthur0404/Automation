package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UserHelper {

    public static final String EMAIL = "qualityA2019+TA1@gmail.com";
    public static final String PASSWORD = "TopHap";

    public static void login(WebDriver driver, String email, String password) {
        driver.findElement(By.xpath("//a[@class='th-signin-button']")).click();
        driver.switchTo().activeElement();
        driver.findElement(By.xpath( "//input[@placeholder='E-mail']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@class='MuiButtonBase-root th-button']")).click();
    }
    public static void logout(WebDriver driver) {
        TestHelper.moveToElement(driver, By.className("UserAvatar--inner"));
        driver.findElement(By.xpath("//li[text()='Sign Out']")).click();
    }

    public static void openUserProfile(WebDriver driver) {
        TestHelper.moveToElement(driver, By.className("UserAvatar--inner"));
        driver.findElement(By.linkText("Account")).click();
    }

    // First option:
    public static void emailConfirmationFailureMsgClose(WebDriver driver) {
        List<WebElement> emailConfirmationFailureMsg = driver.findElements(
                By.xpath("//div[@class='Toastify__toast-container Toastify__toast-container--top-right th-notification-container']"));
        if (emailConfirmationFailureMsg.size() > 0) {
            driver.findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-close-button']")).click();
        }
    }

//    // Second option (which one is better?):
//    public static void emailConfirmationFailureMsgClose(WebDriver driver) {
//        WebElement waitForSignUpWindow = new WebDriverWait(driver, 10).until(TestHelper.movingIsFinished(
//                By.xpath("//div[@class='Toastify__toast-container Toastify__toast-container--top-right th-notification-container']")));
//            driver.findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-close-button']")).click();
//    }
}
