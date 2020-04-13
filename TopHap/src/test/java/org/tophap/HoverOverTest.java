package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.tophap.runner.MultipleTest;
import org.tophap.runner.SingleTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoverOverTest extends MultipleTest {

    @Test
    void valueEstimatesTest() {

        TestHelper.loginTheSite(getDriver());

        Actions action = new Actions(getDriver());
        List<WebElement> buttons = getDriver().findElements(By.xpath("//span[text()='Value Estimates']"));
        action.moveToElement(buttons.get(0)).perform();

        assertTrue(getDriver().findElement(By.xpath("//div[text()='Estimated Property Values']")).isDisplayed());
    }

    @Test
    void ageTest() {

        TestHelper.loginTheSite(getDriver());
        getDriver().manage().window().maximize();

        Actions action = new Actions(getDriver());
        List<WebElement> buttons = getDriver().findElements(By.xpath("//span[text()='Age']"));
        action.moveToElement(buttons.get(0)).perform();

        assertTrue(getDriver().findElement(By.xpath("//div[text()='Property Age (years)']")).isDisplayed());
    }

    @Test
    void ownershipTimeTest() {

        TestHelper.loginTheSite(getDriver());
        getDriver().manage().window().maximize();

        Actions action = new Actions(getDriver());
        List<WebElement> buttons = getDriver().findElements(By.xpath("//span[text()='Ownership Time']"));
        action.moveToElement(buttons.get(0)).perform();

        assertTrue(getDriver().findElement(By.xpath("//div[text()='Current Ownership Time (days)']")).isDisplayed());
    }
    @Disabled
    @Test
    void ListSoldTest() {

        TestHelper.loginTheSite(getDriver());
        getDriver().manage().window().maximize();

        Actions action = new Actions(getDriver());
        List<WebElement> buttons = getDriver().findElements(By.xpath("//span[text()='List vs Sold']"));
        action.moveToElement(buttons.get(0)).perform();
        // tooltip text needs to be checked by product owner
        assertTrue(getDriver().findElement(By.xpath("//div[text()='List Price to Sell Price Ratio (%)']")).isDisplayed());
    }

    @Disabled
    @Test
    void propertyUseTest() {

        TestHelper.loginTheSite(getDriver());
        getDriver().manage().window().maximize();

        Actions action = new Actions(getDriver());
        List<WebElement> buttons = getDriver().findElements(By.xpath("//span[text()='Property Use']"));
        action.moveToElement(buttons.get(0)).perform();
        //tool tip does not appear
        assertTrue(getDriver().findElement(By.xpath("//div[text()='Property User']")).isDisplayed());
    }
    @Test
    void bedroomCountTest() {
        TestHelper.loginTheSite(getDriver());

        Actions action = new Actions(getDriver());
        List<WebElement> buttons = getDriver().findElements(By.xpath("//span[text()='Bedroom Count']"));
        action.moveToElement(buttons.get(0)).perform();

        assertTrue(getDriver().findElement(By.xpath("//div[text()='Property Number of Bedrooms']")).isDisplayed());
    }
}

