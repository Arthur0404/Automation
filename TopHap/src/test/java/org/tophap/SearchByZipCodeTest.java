package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tophap.runner.SingleTest;
import pages.HomePage;
import pages.LoginPage;
import pages.MapPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchByZipCodeTest extends SingleTest {

    private static final By REGION_LOCATOR = By.cssSelector(".th-region");

    private String getZipFromRegion(String region) {
        return region.substring(region.length() - 5);
    }

    @Test
    void returnedResultsAreInSearchedZipCodeArea() throws InterruptedException {

        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.openLogin();
        loginPage.login(UserHelper.EMAIL, UserHelper.PASSWORD);
        homePage.closeEmailConfirmationFailureMsg();

        MapPage mapPage = homePage.tryForFreeStart();
        mapPage.submitSearch(MapPage.ZIP_TEST);

        int count = mapPage.forEachItemInSearchResult(
                element -> {
                    WebElement currentElement = element.findElement(REGION_LOCATOR);
                    String zipCode = getZipFromRegion(currentElement.getText());
                    assertEquals(MapPage.ZIP_TEST, zipCode);
                });

        assertTrue(count > 0, "No items in search results");
    }
}
