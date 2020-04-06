package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.runner.SingleTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateFreeAccount extends SingleTest {

    @Disabled
    @Test
    void createFreeAccount() throws InterruptedException {
        // open Sign up form from the Home page
        String emailRandom  = "qualityA2019+TA" + Math.round(Math.random()*1000) + "@gmail.com";
        String name = "TopHap";
        String password = "TopHap";
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        getDriver().findElement(By.className("th-signup-button")).click();
        WebElement waitForSignUpWindow = new WebDriverWait(getDriver(), 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']//h1")));

        // verify you are on the Sign up form
        String signUpPageHeading = getDriver().findElement(By.xpath("//div[@role='dialog']//h1")).getText();
        assertEquals(signUpPageHeading, "Sign Up for Free");
        Thread.sleep(3000); // temporary solution to handle advert modal window
        getDriver().navigate().refresh(); // temporary solution to handle advert modal window

        // populate Sign up form and Submit
        getDriver().findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
        getDriver().findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(emailRandom);
        getDriver().findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        // verify confirmation modal window is displayed and has correct information
        assertTrue(getDriver().findElement(By.className("th-authentication-modal")).isDisplayed());
        String modalWindowHeading = "Welcome to TopHap";
        assertEquals(getDriver().findElement(By.xpath("//section[@class='mx-4 px-2']//h1")).getText(), modalWindowHeading);
        String modalWindowText = "Thank you for registering! Your account has been created successfully.";
        assertEquals(getDriver().findElement(By.xpath("//section[@class='mx-4 px-2']//p")).getText(), modalWindowText);
        assertTrue(getDriver().findElement(By.xpath("//button[text()='RESEND EMAIL']")).isEnabled());
        getDriver().findElement(By.xpath("//button[text()='OK']")).click();

        // verify that you are automatically logged in
        String avatarName = getDriver().findElement(By.xpath("//div[contains(@class,'UserAvatar')]")).getAttribute("aria-label");
        assertEquals(avatarName, name);
        getDriver().navigate().refresh(); // temporary solution to handle advert modal window
        Thread.sleep(1000); // temporary solution to handle advert modal window

        // verify that you subscription is free and you are not connected to any plan yet
        List<WebElement> plans = getDriver().findElements(By.xpath("//div[contains(@class,'th-plan-info')]//button"));
        for (WebElement plan : plans) {
            String planBtnTxt = plan.getText();
            boolean isEqual = planBtnTxt.equals("Get Started") || planBtnTxt.equals("Call Us");
            assertTrue(isEqual);
        }

        // delete just created account from the system
        getDriver().findElement(By.xpath("//div[contains(@class,'UserAvatar')]")).click();
        getDriver().findElement(By.linkText("Account")).click();
        getDriver().findElement(By.className("th-close-account-button")).click();
        getDriver().findElement(By.xpath("//div[contains(@class,'th-alert-modal')]//button[contains(@class,'th-ok-action')]")).click();
        WebElement waitForSignUpButton = new WebDriverWait(getDriver(), 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("th-signup-button")));
    }
}