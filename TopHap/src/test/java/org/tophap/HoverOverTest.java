package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.tophap.runner.SingleTest;
import pages.HomePage;
import pages.MapPage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoverOverTest extends SingleTest {

    private static final Map<String, String> buttonsWithHoverOvers = new HashMap<>();

    static {
        buttonsWithHoverOvers.put("Listings", "Properties");
        buttonsWithHoverOvers.put("Value Estimates", "Estimated Property Values");
        buttonsWithHoverOvers.put("$/ft² Estimates", "Estimated Price per Square Foot");
        buttonsWithHoverOvers.put("Living Area", "Property Living Area (square feet)");
        buttonsWithHoverOvers.put("Bedroom Count", "Property Number of Bedrooms");
        buttonsWithHoverOvers.put("Bathroom Count", "Property Number of Bathrooms");
        buttonsWithHoverOvers.put("Lot Size", "Property Lot Size (acres)");
        buttonsWithHoverOvers.put("Age", "Property Age (years)");
        buttonsWithHoverOvers.put("Ownership Time", "Current Ownership Time (days)");
        buttonsWithHoverOvers.put("DOM", "Days on market");
        buttonsWithHoverOvers.put("List vs Sold", "List Price to Sell Price Ratio (%)");
        buttonsWithHoverOvers.put("Walkability", "National Walkability Index");
        buttonsWithHoverOvers.put("Elevation", "Elevation above sea level");
        buttonsWithHoverOvers.put("Permits", "Permits");
    }

    @Test
    void hoverOverTest() throws InterruptedException {

        HomePage homePage = new HomePage(getDriver());
        MapPage mapPage = homePage.tryForFreeStart();

        for (Map.Entry<String, String> entry : buttonsWithHoverOvers.entrySet()) {
                        TestHelper.moveToHiddenElement(getDriver(), getDriver().findElement(By.xpath(
                    String.format("//span[text()='%s']", entry.getKey()))), mapPage.moreContainerBtn);
            assertTrue(getDriver().findElement(By.xpath(String.format("//div[text()='%s']", entry.getValue()))).isDisplayed());
        }
    }
}