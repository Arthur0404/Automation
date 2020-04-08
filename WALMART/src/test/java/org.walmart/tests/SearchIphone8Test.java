package org.walmart.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.walmart.pages.HomePage;
import org.walmart.runner.SingleTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchIphone8Test extends SingleTest {

    @Test
    void iphone8NextDayDeliveryTest() {

        HomePage hp = new HomePage(getDriver());

        getDriver().get(hp.HOME_URL);
        getDriver().manage().window().maximize();

        hp.globalSearchInput.sendKeys("iphone8");
        hp.globalSearchBtn.click();

        assertTrue(hp.searchResultList.size() > 0);

        hp.nextDayDeliveryBtn.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(hp.cssNextDayDeliveryMessage));
        hp.cssNextDayDeliveryMessage.click();

        assertTrue(hp.noNextDayDeliveryLocator.getText()
                .contains("No results found in NextDay with your current filter."));

    }
}


