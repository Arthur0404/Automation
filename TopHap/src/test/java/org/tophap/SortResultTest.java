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

        int prevPrice = Integer.MIN_VALUE;
        for (WebElement searchResultItem : mapPage.searchResultList) {
            int currentPrice = Integer.parseInt(searchResultItem.findElement(By.className("th-price")).getText().replaceAll("[$,]", ""));
            assertTrue(prevPrice <= currentPrice, String.format("$%d more than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
        }
    }

    @Test
    @Order(3)
    void sortZAPriceTest() throws InterruptedException {
        MapPage mapPage = new MapPage(getDriver());
        mapPage.submitSearchApplySortingAndFilters(mapPage.sortZABtn);

        int prevPrice = Integer.MAX_VALUE;
        for (WebElement searchResultItem : mapPage.searchResultList) {
            int currentPrice = Integer.parseInt(searchResultItem.findElement(By.className("th-price")).getText().replaceAll("[$,]", ""));
            assertTrue(prevPrice >= currentPrice, String.format("$%d less than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
        }
    }
}
