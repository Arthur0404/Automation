package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    public static void goToBilling(WebDriver driver) {
        TestHelper.moveToElement(driver, By.xpath( "//div[@class='jsx-3275066862 th-menu-item th-avatar-wrapper']"));
        driver.findElement(By.xpath("//ul//a[text()='Billing']")).click();
    }
}
