package org.tophap;

import org.junit.jupiter.api.Test;
import org.tophap.runner.SingleTest;
import pages.HomePage;
import pages.MapPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoverOverTest extends SingleTest {

    @Test
    void hoverOverTest() throws InterruptedException {

        HomePage homePage = new HomePage(getDriver());

        MapPage mapPage = homePage.tryForFreeStart();

        mapPage.forEachButtonInAnalyticMenu(eachButtonsHoverOver -> assertTrue(eachButtonsHoverOver.isDisplayed()));
    }
}