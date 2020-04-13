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

public class HoverOverTest extends SingleTest {

    private final String[] BUTTONS_NAMES = {
            "Listings",
            "Value Estimates",
            "$/ft² Estimates",
            "Living Area",
            "Bedroom Count",
            "Bathroom Count",
            "Lot Size",
            "Age",
            "Ownership Time",
            "DOM",
            "List vs Sold",
            "Permits"
    };

    private final String[] HOOVER_OVER_TEXTS = {
            "Properties",
            "Estimated Property Values",
            "Estimated Price per Square Foot",
            "Property Living Area (square feet)",
            "Property Number of Bedrooms",
            "Property Number of Bathrooms",
            "Property Lot Size (acres)",
            "Property Age (years)",
            "Current Ownership Time (days)",
            "Days on market",
            "List Price to Sell Price Ratio (%)",
            "Permits"
    };

    @Test
    void hoverOverTest() {

        TestHelper.loginTheSite(getDriver());
        getDriver().manage().window().maximize();

        for (int i = 0; i < BUTTONS_NAMES.length; i++) {
            TestHelper.moveToElement(getDriver(), By.xpath("//span[text()='" + BUTTONS_NAMES[i] + "']"));
            assertTrue(getDriver().findElement(By.xpath("//div[text()='" + HOOVER_OVER_TEXTS[i] + "']")).isDisplayed());
        }
    }
}

