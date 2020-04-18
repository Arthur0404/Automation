package org.tophap;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;


import java.util.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.*;
import org.tophap.runner.*;

import static org.junit.jupiter.api.Assertions.*;

public class PricingProAnnualUnsignedTest extends SingleTest {

    @Test
    void pricingProAnnualUnsigned() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);

        //Go to Home --> Pricing Page
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        TestHelper.selectPricing(getDriver());

        //List of 4 Plans to check the presence and titles
        final String[] PLANS = {
                "Free",
                "Pro",
                "Advanced",
                "Enterprise"
        };

        List<WebElement> pricePlans = getDriver().findElements(By.xpath("//h5"));

        int i = 0;
        for (String el : PLANS) {
            assertEquals(el, pricePlans.get(i).getText());
            i++;
        }

        //Annual plan pre-selected by default with $45 per month on Pro plan
        WebElement annualBill18 = getDriver().findElement(By.xpath("//div[@class='th-period-picker']/button[1]"));
        assertTrue(annualBill18.getAttribute("class").contains("selected"));
        assertTrue(getDriver().findElement(By.xpath("//div[@class='th-plan-info th-recommended']//*[@class='th-price']")).
                getText().contains("45"));

        //Choose Pro plan clicking `Get Started` button
        WebElement proPlanButton=getDriver().findElement(By.xpath("//div[@class='th-plan-info th-recommended']//button"));
        proPlanButton.click();

        //Sign in with pre-registered user
        TestHelper.signIn(getDriver(), UserHelper.EMAIL, UserHelper.PASSWORD);
        TestHelper.emailConfirmationFailureMsgClose(getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(proPlanButton));

        //Enter Credit Card data
        proPlanButton.click();
        //Thread.sleep(2000);

        WebElement iFramePayment = getDriver().findElement(By.xpath("//iframe[@title='Secure payment input frame']"));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFramePayment));
        Thread.sleep(2000);

        TestHelper.enterCreditCardData(getDriver(), TestHelper.CREDIT_CARD, TestHelper.CREDIT_CARD_EXPIRATION,
                TestHelper.CREDIT_CARD_CVV, TestHelper.ZIP_CODE);
        getDriver().switchTo().parentFrame();

        getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-submit-button']")).click();
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement
                (By.xpath("//*/button[@class='MuiButtonBase-root th-button th-cancel-button']"))));

        //Go to Account --> Billing --> Cancel Plan --> Remove payment Method and Unsubscribe from Pro Plan
        UserHelper.goToBilling(getDriver());
        getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-cancel-button']")).click();
        WebElement paymentMethodRemoveButton=getDriver().findElement(By.xpath("//span[@class='jsx-844615980 th-card-last4']"));
        //assertTrue(paymentMethodRemoveButton.getText().endsWith(TestHelper.CREDIT_CARD_4LAST_DIGITS));
        getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-action-button " +
                "th-cancel-button']")).click();
        wait.until(ExpectedConditions.invisibilityOf(paymentMethodRemoveButton));

        //Go to Pricing Menu and verify The Pro plan is cancelled and user is on a Free plan
        TestHelper.selectPricing(getDriver());
        assertTrue(getDriver().findElement(By.xpath("//span[text()='You are free member now']")).isDisplayed());
        }

    }


