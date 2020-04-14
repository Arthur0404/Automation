package org.walmart.tests;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.walmart.pages.SearchPage;
import org.walmart.runner.SingleTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NextDayDeliveryTest extends SingleTest {

    private boolean nextDayDeliveryAll (List<WebElement> list) {
        for (WebElement el : list) {
            if (!el.getText().contains("NextDay eligible"))
                return false;
        }
        return true;
    }

    @Disabled
    @Test
    void nextDayDeliveryTest() {
        boolean bool = true;
        SearchPage sp = new SearchPage(getDriver());

        getDriver().get(sp.search_url);
        getDriver().manage().window().maximize();

        sp.globalSearch("toys for girls 3-6 years");
        sp.nextDayDeliveryClick();

        String[] searchResultText =
                sp.textSearchResult.getText().split(" ");

        int numberOfSearchElements = Integer.parseInt(searchResultText[5]);
        int numberOfPages = sp.numberOfPages.size();
        System.out.println(numberOfPages);

        List<WebElement> result = new ArrayList<>();

        for (int i = 1; i <= numberOfPages; i++)
        {
            List<WebElement> resultPage = sp.searchResultList;
            if(!nextDayDeliveryAll(resultPage)) {
                bool = false;
                break;
            }
            result.addAll(resultPage);
            if (i == numberOfPages) {
                break;
            }

            WebElement element = sp.nextPage;
            JavascriptExecutor executor = (JavascriptExecutor)getDriver();
            executor.executeScript("arguments[0].click();", element);

            getDriver().get(getDriver().getCurrentUrl());
        }

        assertEquals(numberOfSearchElements, result.size());
        assertTrue(bool);
    }
}

