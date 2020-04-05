package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tophap.runner.MultipleTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortResultTest extends MultipleTest {

    @Disabled
    @Test
    @Order(1)
    void loginTest() {
        getDriver().get("https://www.tophap.com/");

        getDriver().findElement(By.xpath("//button[text()='Try for Free']")).click();
        getDriver().findElement(By.cssSelector("svg.th-close-button")).click();
    }

    @Disabled
    @Test
    @Order(2)
    void sortAZPriceTest() throws InterruptedException {
        WebElement searchInput = getDriver().findElement(By.id("th-geo-input"));
        List<WebElement> searchInputClear = getDriver().findElements(By.className("th-clear-button"));
        if (searchInputClear.size() > 0) searchInputClear.get(0).click();
        searchInput.sendKeys("94523");

        Thread.sleep(2000);
        getDriver().findElement(By.className("th-search-button")).click();

        // waiting for menu
        getDriver().findElement(By.xpath("//aside[@class='th-sider']"));

        WebElement menu = getDriver().findElement(By.className("th-trigger"));
        menu.click();

        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//button[text()='A-Z']")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//button[text()='Price']")).click();
        menu.click();

        List<WebElement> resultList = getDriver().findElements(By.className("th-result-item-wrapper"));
        int prevPrice = Integer.MIN_VALUE;
        for (WebElement resultItem : resultList) {
            int currentPrice = Integer.parseInt(resultItem.findElement(By.className("th-price")).getText().replaceAll("[$,]", ""));
            assertTrue(prevPrice <= currentPrice, String.format("$%d more than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
        }

    }

    @Disabled
    @Test
    @Order(3)
    void sortZAPriceTest() throws InterruptedException {
        WebElement searchInput = getDriver().findElement(By.id("th-geo-input"));
        List<WebElement> searchInputClear = getDriver().findElements(By.className("th-clear-button"));
        if (searchInputClear.size() > 0) searchInputClear.get(0).click();
        searchInput.sendKeys("94523");

        Thread.sleep(2000);
        getDriver().findElement(By.className("th-search-button")).click();

        // waiting for menu
        getDriver().findElement(By.xpath("//aside[@class='th-sider']"));

        WebElement menu = getDriver().findElement(By.className("th-trigger"));
        menu.click();

        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//button[text()='Z-A']")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//button[text()='Price']")).click();
        menu.click();

        List<WebElement> resultList = getDriver().findElements(By.className("th-result-item-wrapper"));
        int prevPrice = Integer.MAX_VALUE;
        for (WebElement resultItem : resultList) {
            int currentPrice = Integer.parseInt(resultItem.findElement(By.className("th-price")).getText().replaceAll("[$,]", ""));
            assertTrue(prevPrice >= currentPrice, String.format("$%d less than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
        }
    }
}
