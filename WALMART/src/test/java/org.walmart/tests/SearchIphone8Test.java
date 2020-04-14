package org.walmart.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.walmart.pages.SearchPage;
import org.walmart.runner.SingleTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchIphone8Test extends SingleTest {

    @Disabled
    @Test
    void iphone8NextDayDeliveryTest() {

        SearchPage sp = new SearchPage(getDriver());
        String noResult = "No results found in NextDay with your current filter.";

        getDriver().get(sp.search_url);
        getDriver().manage().window().maximize();

        sp.globalSearch("iphone8");
        assertTrue(sp.searchResultList.size() > 0);

        sp.nextDayDeliveryClick();

        assertTrue(sp.noNextDayDeliveryLocator.getText()
                .contains(noResult));
    }
}


