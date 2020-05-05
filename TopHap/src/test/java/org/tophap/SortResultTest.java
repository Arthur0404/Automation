package org.tophap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.tophap.runner.MultipleTest;
import pages.HomePage;
import pages.MapPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortResultTest extends MultipleTest {

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

        mapPage.submitSearchApplySortingAndFilters(mapPage.sortAZBtn);

        mapPage.forEachItemInSearchResult(currentPrice -> assertTrue(mapPage.prevPrice <= currentPrice), Integer.MIN_VALUE);
    }

    @Test
    @Order(3)
    void sortZAPriceTest() throws InterruptedException {

        MapPage mapPage = new MapPage(getDriver());

        mapPage.submitSearchApplySortingAndFilters(mapPage.sortZABtn);

        mapPage.forEachItemInSearchResult(currentPrice -> assertTrue(mapPage.prevPrice >= currentPrice), Integer.MAX_VALUE);
    }
}