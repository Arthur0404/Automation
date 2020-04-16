package org.walmart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage {

    public String search_url = "https://www.walmart.com/search";

    @FindBy(id = "global-search-input")
    public WebElement globalSearchInput;

    @FindBy(id = "global-search-submit")
    public WebElement globalSearchBtn;

    @FindBys({
        @FindBy(xpath = "//div[@id='searchProductResult']/ul/li")
    })
    public List<WebElement> searchResultList;

    @FindBy(xpath = "//label[@class='label-nd-toggle']")
    public WebElement nextDayDeliveryBtn;

    @FindBy(css = "div#nd-message-close > img")
    public WebElement cssNextDayDeliveryMessage;

    @FindBy(xpath = "//header[@class='header']")
    public WebElement noNextDayDeliveryLocator;

    @FindBy(xpath = "//div[@class='result-summary-container']")
    public WebElement textSearchResult;

    @FindBys({
        @FindBy(xpath = "//ul[@class='paginator-list']/li/a")
    })
    public List<WebElement> numberOfPages;

    @FindBy(xpath = "//button[@class='elc-icon paginator-hairline-btn paginator-btn paginator-btn-next outline']")
    public WebElement nextPage;


    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void globalSearch (String search) {
        globalSearchInput.sendKeys(search);
        globalSearchBtn.click();
    }

    public void nextDayDeliveryClick () {
        nextDayDeliveryBtn.click();
        cssNextDayDeliveryMessage.click();
    }
}
