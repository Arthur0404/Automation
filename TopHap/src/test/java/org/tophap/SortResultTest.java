package org.tophap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.runner.MultipleTest;
import pages.HomePage;
import pages.MapPage;


import java.util.List;

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
        int i = 0;
        List<WebElement> initialSearchResultsPricesList = getDriver().findElements(By.cssSelector(".th-item-wrapper .th-price"));
        while (true) {
            System.out.println("Number of search result items " + initialSearchResultsPricesList.size());
            if (i == initialSearchResultsPricesList.size() - 1) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", initialSearchResultsPricesList.get(i));
                new WebDriverWait(getDriver(), 10).until(TestHelper.movingIsFinished(initialSearchResultsPricesList.get(i)));
                List<WebElement> currentSearchResultsPricesList = getDriver().findElements(By.cssSelector(".th-item-wrapper .th-price"));
                i = 1;
                if (!currentSearchResultsPricesList.equals(initialSearchResultsPricesList)) {
                    initialSearchResultsPricesList = currentSearchResultsPricesList;
                } else {
                    break;
                }
            }
            int currentPrice = Integer.parseInt(initialSearchResultsPricesList.get(i).getText().replaceAll("[$,]", ""));
            System.out.println("Price of search result item " + currentPrice);
            assertTrue(prevPrice <= currentPrice, String.format("$%d more than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
            i++;
        }
    }

    @Test
    @Order(3)
    void sortZAPriceTest() throws InterruptedException {
        MapPage mapPage = new MapPage(getDriver());
        mapPage.submitSearchApplySortingAndFilters(mapPage.sortZABtn);

        int prevPrice = Integer.MAX_VALUE;
        int i = 0;
        List<WebElement> initialSearchResultsPricesList = getDriver().findElements(By.cssSelector(".th-item-wrapper .th-price"));
        while (true) {
            System.out.println("Number of search result items " + initialSearchResultsPricesList.size());
            if (i == initialSearchResultsPricesList.size() - 1) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", initialSearchResultsPricesList.get(i));
                new WebDriverWait(getDriver(), 10).until(TestHelper.movingIsFinished(initialSearchResultsPricesList.get(i)));
                List<WebElement> currentSearchResultsPricesList = getDriver().findElements(By.cssSelector(".th-item-wrapper .th-price"));
                i = 1;
                if (!currentSearchResultsPricesList.equals(initialSearchResultsPricesList)) {
                    initialSearchResultsPricesList = currentSearchResultsPricesList;
                } else {
                    break;
                }
            }
            int currentPrice = Integer.parseInt(initialSearchResultsPricesList.get(i).getText().replaceAll("[$,]", ""));
            System.out.println("Price of search result item " + currentPrice);
            assertTrue(prevPrice >= currentPrice, String.format("$%d more than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
            i++;
        }
    }
}