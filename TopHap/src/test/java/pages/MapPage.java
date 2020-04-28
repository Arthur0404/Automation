package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.TestHelper;

import java.util.List;

public class MapPage extends BasePage {

    @FindBy(id = "th-geo-input")
    public WebElement searchInputField;

    @FindBys({
            @FindBy(className = "th-clear-button")
    })
    public List<WebElement> clearSeachBtns;

    @FindBys({
            @FindBy(className = "th-clear-icon")
    })
    public List<WebElement> clearFilterBtns;

    @FindBy(className = "th-search-button")
    public WebElement searchButton;

    @FindBy(xpath = "//aside[contains(@class,'th-sider')]")
    public WebElement searchResultsMenu;

    @FindBy(className = "th-trigger")
    public WebElement sortMenu;

    @FindBy(xpath = "//button[text()='A-Z']")
    public WebElement sortAZBtn;

    @FindBy(xpath = "//button[text()='Z-A']")
    public WebElement sortZABtn;

    @FindBy(xpath = "//button[text()='Price']")
    public WebElement selectPriceSorting;

    @FindBy(xpath = "//div[text()='Property Status']")
    public WebElement propertyStatusFilterMenu;

    @FindBy(xpath = "//label//span[text()='Active']")
    public WebElement activePropertyFilter;

    @FindBys({
            @FindBy(className = "th-item-wrapper")
    })
    public List<WebElement> searchResultList;

    @FindBy(xpath = "//div[@class='jsx-1707507361 th-popover th-popover--expanded th-status-option']")
    public WebElement filterDropDownMenu;

    public MapPage(WebDriver driver) {
        super(driver);
    }

    public void clearSearchField() {
        clearSeachBtns.get(0).click();
    }

    public void clearPropertyStatusFilter() {
        clearFilterBtns.get(0).click();
    }

    public void clearOldSearchAndFilterRecords() {
        if (this.clearSeachBtns.size() > 0) {
            this.clearSearchField();
        }
        if (this.clearFilterBtns.size() > 0) {
            this.clearPropertyStatusFilter();
        }
    }

    public void submitSearch(String postalCode) throws InterruptedException {
        this.searchInputField.sendKeys(postalCode);
        Thread.sleep(2000);
        this.searchButton.click();
    }

    public void submitSearchApplySortingAndFilters(WebDriver driver, WebElement orderAtoZorZtoA) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.clearOldSearchAndFilterRecords();
        this.submitSearch("94523");
        wait.until(ExpectedConditions.visibilityOf(this.searchResultsMenu));
        this.sortMenu.click();
        orderAtoZorZtoA.click();
        this.selectPriceSorting.click();
        this.propertyStatusFilterMenu.click();
        wait.until(ExpectedConditions.visibilityOf(this.filterDropDownMenu));
        this.activePropertyFilter.click();
    }
}