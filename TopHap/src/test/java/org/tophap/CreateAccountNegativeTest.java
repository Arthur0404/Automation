package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.tophap.runner.SingleTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CreateAccountNegativeTest extends SingleTest {

    @Test
    void attemptToCreateAccountWithFailedData() throws InterruptedException {
        // open Sign up form from the Home page
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        getDriver().findElement(By.className("th-signup-button")).click();

        // verify you are on the Sign up form
        assertEquals("Sign Up for Free", getDriver().findElement(By.xpath("//div[@role='dialog']//h1")).getText());

        // populate Sign up form (Name field leave empty) and Submit
        getDriver().findElement(By.xpath("//input[@placeholder='Email']"))
                .sendKeys("qualityA2019+TA" + Math.round(Math.random()*1000) + "@gmail.com");
        getDriver().findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(UserHelper.PASSWORD);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        // verify the Name field is mandatory and that you are left of the Sign up form
        assertTrue(getDriver().findElements(By.xpath("//input[@placeholder='Name' and @required]")).size() == 1);
        assertEquals("Sign Up for Free", getDriver().findElement(By.xpath("//div[@role='dialog']//h1")).getText());
    }
}