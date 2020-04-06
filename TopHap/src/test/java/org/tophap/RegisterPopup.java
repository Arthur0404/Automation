package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.tophap.runner.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterPopup extends BaseTest {

    @Disabled
    @Test
    void registerPopup() throws InterruptedException {
        String emailRandom  = Math.random() + "+TA@example.com";
        getDriver().get("https://next.tophap.com/");
        getDriver().findElement(By.className("th-signup-button")).click();
        getDriver().switchTo().activeElement();
        getDriver().findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John Doe");
        getDriver().findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(emailRandom);
        getDriver().findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123456");
        getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-signup-button']")).click();
        assertTrue(getDriver().findElement(By.className("th-authentication-modal")).isDisplayed());
    }
}
