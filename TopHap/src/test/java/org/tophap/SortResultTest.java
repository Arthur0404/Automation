package org.tophap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tophap.runner.MultipleTest;
import pages.HomePage;
import pages.MapPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortResultTest extends MultipleTest {

    private static final By PRICE_LOCATOR = By.cssSelector(".th-price");

    private int getPriceFromText(String price) {
        return Integer.parseInt(price.replaceAll("[$,]", ""));
    }

    @Test
    @Order(1)
    void loginTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.tryForFreeStart();
    }

    @Test
    @Order(2)
    void sortAZPriceTest() throws InterruptedException {

        MapPage mapPage = new MapPage(getDriver());
        mapPage.submitSearchApplySortingAndFiltersAZ();

        int[] prevPrice = {Integer.MIN_VALUE};
        int count = mapPage.forEachItemInSearchResult(
                element -> {
                    WebElement currentElement = element.findElement(PRICE_LOCATOR);
                    int currentPrice = getPriceFromText(currentElement.getText());
                    assertTrue(prevPrice[0] <= currentPrice);
                    prevPrice[0] = currentPrice;
                });

        assertTrue(count > 0, "No items in search results");
    }

    @Test
    @Order(3)
    void sortZAPriceTest() throws InterruptedException {

        MapPage mapPage = new MapPage(getDriver());
        mapPage.submitSearchApplySortingAndFiltersZA();

        int[] prevPrice = {Integer.MAX_VALUE};
        int count = mapPage.forEachItemInSearchResult(
                element -> {
                    WebElement currentElement = element.findElement(PRICE_LOCATOR);
                    int currentPrice = getPriceFromText(currentElement.getText());
                    assertTrue(prevPrice[0] >= currentPrice);
                    prevPrice[0] = currentPrice;
                });

        assertTrue(count > 0, "No items in search results");
    }
}