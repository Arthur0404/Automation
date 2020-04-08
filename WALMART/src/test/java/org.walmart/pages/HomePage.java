package org.walmart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage {

    private WebDriver driver;
    public final String HOME_URL = "https://www.walmart.com";

    @FindBy(id = "global-search-input")
    public WebElement globalSearchInput;

    @FindBy(id = "global-search-submit")
    public WebElement globalSearchBtn;

    @FindBy(id = "searchProductResult")
    public List<WebElement> searchResultList;

    @FindBy(css = "label.label-nd-toggle")
    public WebElement nextDayDeliveryBtn;

    @FindBy(css = "//header[text()='No results found in NextDay with your current filter.']")
    public WebElement noNextDayDelivery;

    @FindBy(css = "div#nd-message-close > img")
    public WebElement cssNextDayDeliveryMessage;

    @FindBy(xpath = "//header[@class='header']")
    public WebElement noNextDayDeliveryLocator;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
