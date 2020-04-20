package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tophap.runner.SingleTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateFreeAccountTest extends SingleTest {

    @Disabled
    @Test
    void createFreeAccount() throws InterruptedException {
        // open Sign up form from the Home page and sign up
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        UserHelper.signUp(getDriver(), UserHelper.NAME, "qualityA2019+TA" + Math.round(Math.random()*1000) + "@gmail.com", UserHelper.PASSWORD);

        // verify confirmation modal window is displayed and has correct information
        assertTrue(getDriver().findElement(By.className("th-authentication-modal")).isDisplayed());
        assertEquals("Welcome to TopHap",
                getDriver().findElement(By.xpath("//section[@class='mx-4 px-2']//h1")).getText());
        assertTrue(getDriver().findElement(By.xpath("//button[text()='RESEND EMAIL']")).isEnabled());
        getDriver().findElement(By.xpath("//button[text()='OK']")).click();

        // verify that you are automatically logged in (avatar name on the screen equals name)
        assertEquals(UserHelper.NAME, getDriver().findElement(By.xpath("//div[contains(@class,'UserAvatar')]")).getAttribute("aria-label"));
        getDriver().navigate().refresh();
        TestHelper.closeWelcome(getDriver());

        // verify that your subscription is free and you are not connected to any plan yet
        List<WebElement> plans = getDriver().findElements(By.xpath("//div[contains(@class,'th-plan-info')]//button"));
        for (WebElement plan : plans) {
            String planBtnTxt = plan.getText();
            assertTrue(planBtnTxt.equals("Get Started") || planBtnTxt.equals("Call Us"));
        }

        // delete just created account from the system
        getDriver().findElement(By.xpath("//div[contains(@class,'UserAvatar')]")).click();
        getDriver().findElement(By.linkText("Account")).click();
        getDriver().findElement(By.className("th-close-account-button")).click();
        getDriver().findElement(By.xpath("//div[contains(@class,'th-alert-modal')]//button[contains(@class,'th-ok-action')]")).click();
        assertTrue(getDriver().findElement(By.className("th-signup-button")).isDisplayed());
    }
}