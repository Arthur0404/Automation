package org.tophap.runner;

import com.sun.org.apache.xml.internal.security.utils.*;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.*;
import org.openqa.selenium.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.*;

public class PricingProAnnualUnsigned extends SingleTest {

    @Test
    void pricingProAnnualUnsigned() throws InterruptedException {
        getDriver().get("https://next.tophap.com");
        getDriver().manage().window().maximize();
        TestHelper.PricingMenu(getDriver());
        String pricingPageTitle = getDriver().getCurrentUrl();
        assertEquals(pricingPageTitle, "https://next.tophap.com/pricing");

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

        //Annual plan pre-selected by default
        WebElement annualBill18 = getDriver().findElement(By.xpath("//div[@class='th-period-picker']/button[1]"));
        assertTrue(annualBill18.getAttribute("class").contains("selected"));
        Thread.sleep(3000);
        //Cost to be $45.00 per month by default on Annual plan
        assertTrue(getDriver().findElement(By.xpath("//div[@class='th-plan-info th-recommended']//*[@class='th-price']")).
                getText().contains("45"));

        /*At some point there wat iFrame from Intercom welcoming us at a super secret NEXT version, which we need to close
        getDriver().switchTo().frame("intercom-modal-frame");
        if (getDriver().findElement(By.xpath("//div[@class='intercom-post intercom-9j7c4n e1uu5mk81']")).isDisplayed()){
        getDriver().findElement(By.xpath("//div[@class='intercom-post intercom-9j7c4n e1uu5mk81']//span[@aria-label='Close']")).click();
        Thread.sleep(3000);
        */

        //Choose Pro plan clicking `Get Started` button
        WebElement getStartedButtonProPlan = getDriver().findElement(By.xpath("//*[text()='Get Started']"));
        getStartedButtonProPlan.click();

        //Sign in with pre-registered user
        TestHelper.signIn(getDriver());
        getStartedButtonProPlan.click();

        //Enter Billing Data (after switching to iFrame)
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement iFramePayment = getDriver().findElement(By.xpath("//iframe[@title='Secure payment input frame']"));
        getDriver().switchTo().frame(iFramePayment);
        Thread.sleep(2000);
        TestHelper.enterCreditCardData(getDriver());
        Thread.sleep(2000);

        //After Credit Card data is submitted the `Get Started` Button shall be switched to `Cancel` button
        WebElement newCancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*/button[@class='MuiButtonBase-root th-button th-cancel-button']")));
        assertTrue(newCancelButton.getText().equals("Cancel"));
        Thread.sleep(2000);


        //Go to Account --> Billing --> Cancel Plan --> Remove payment Method and Unsubscribe
        TestHelper.profileDropMenu(getDriver());
        assertEquals(getDriver().findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("value"),
                TestHelper.EMAIL);
        Thread.sleep(3000);
        TestHelper.billingAccountManager(getDriver());
        getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-cancel-button']")).click();
        Thread.sleep(3000);
        assertTrue(getDriver().findElement(By.xpath("//span[@class='jsx-844615980 th-card-last4']"))
                .getText().contains(TestHelper.CCARD.substring(TestHelper.CCARD.length()-4)));
        getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-action-button " +
                "th-cancel-button']")).click();
        Thread.sleep(2000);
        getDriver().navigate().refresh();

        //Go to Pricing Menu and verify The Pro plan is cancelled
        TestHelper.PricingMenu(getDriver());
        Thread.sleep(2000);
        assertTrue(getStartedButtonProPlan.getText().equals("Get Started"));
    }
};
