package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.tophap.runner.SingleTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoverOverTest extends SingleTest {

    @Test
    void valueEstimatesTest() {

        TestHelper.loginTheSite(getDriver());

        Actions action = new Actions(getDriver());
        List<WebElement> buttons = getDriver().findElements(By.xpath("//span[text()='Value Estimates']"));
        action.moveToElement(buttons.get(0)).perform();

        assertTrue(getDriver().findElement(By.xpath("//div[text()='Estimated Property Values']")).isDisplayed());
    }
}
