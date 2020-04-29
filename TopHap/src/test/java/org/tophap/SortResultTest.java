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
        homePage.tryForFreeStart(getDriver());
    }

    @Test
    @Order(2)
    void sortAZPriceTest() throws InterruptedException {
        MapPage mapPage = new MapPage(getDriver());
        mapPage.submitSearchApplySortingAndFilters(getDriver(), mapPage.sortAZBtn);

        System.out.println("Number of search result items " + mapPage.searchResultList.size());
        int prevPrice = Integer.MIN_VALUE;
        for (WebElement searchResultItem : mapPage.searchResultList) {
            int currentPrice = Integer.parseInt(searchResultItem.findElement(By.className("th-price")).getText().replaceAll("[$,]", ""));
            System.out.println("Price of search result item " + currentPrice);
            assertTrue(prevPrice <= currentPrice, String.format("$%d more than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
        }
    }

    @Test
    @Order(3)
    void sortZAPriceTest() throws InterruptedException {
        MapPage mapPage = new MapPage(getDriver());
        mapPage.submitSearchApplySortingAndFilters(getDriver(), mapPage.sortZABtn);

        System.out.println("Number of search result items " + mapPage.searchResultList.size());
        int prevPrice = Integer.MAX_VALUE;
        for (WebElement searchResultItem : mapPage.searchResultList) {
            int currentPrice = Integer.parseInt(searchResultItem.findElement(By.className("th-price")).getText().replaceAll("[$,]", ""));
            System.out.println("Price of search result item " + currentPrice);
            assertTrue(prevPrice >= currentPrice, String.format("$%d less than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
        }
    }
}
