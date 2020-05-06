package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.TestHelper;
import pages.base.MainPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class MapPage extends MainPage {

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
    public WebElement searchBtn;

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
            @FindBy(css = ".th-item-wrapper .th-price")
    })
    public List<WebElement> searchResultsPricesList;

    @FindBy(xpath = "//div[@class='jsx-1707507361 th-popover th-popover--expanded th-status-option']")
    public WebElement filterDropDownMenu;

    @FindBy(className = "th-more-container")
    public WebElement moreContainerBtn;

    private static final Map<String, String> buttonsWithHoverOvers = new HashMap<>();

    static {
        buttonsWithHoverOvers.put("Listings", "Properties");
        buttonsWithHoverOvers.put("Value Estimates", "Estimated Property Values");
        buttonsWithHoverOvers.put("$/ft² Estimates", "Estimated Price per Square Foot");
        buttonsWithHoverOvers.put("Living Area", "Property Living Area (square feet)");
        buttonsWithHoverOvers.put("Bedroom Count", "Property Number of Bedrooms");
        buttonsWithHoverOvers.put("Bathroom Count", "Property Number of Bathrooms");
        buttonsWithHoverOvers.put("Lot Size", "Property Lot Size (acres)");
        buttonsWithHoverOvers.put("Age", "Property Age (years)");
        buttonsWithHoverOvers.put("Ownership Time", "Current Ownership Time (days)");
        buttonsWithHoverOvers.put("DOM", "Days on market");
        buttonsWithHoverOvers.put("List vs Sold", "List Price to Sell Price Ratio (%)");
        buttonsWithHoverOvers.put("Walkability", "National Walkability Index");
        buttonsWithHoverOvers.put("Elevation", "Elevation above sea level");
        buttonsWithHoverOvers.put("Permits", "Permits");
    }

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
        this.searchBtn.click();
    }

    public void submitSearchApplySortingAndFilters(WebElement orderAtoZorZtoA) throws InterruptedException {
        this.clearOldSearchAndFilterRecords();
        this.submitSearch("94523");
        getWait10().until(ExpectedConditions.visibilityOf(this.searchResultsMenu));
        this.sortMenu.click();
        orderAtoZorZtoA.click();
        this.selectPriceSorting.click();
        this.propertyStatusFilterMenu.click();
        getWait10().until(ExpectedConditions.visibilityOf(this.filterDropDownMenu));
        this.activePropertyFilter.click();
    }

    public int prevPrice;

    public void forEachItemInSearchResult(IntConsumer currentResult, int criticalValue) throws InterruptedException {
        prevPrice = criticalValue;
        int i = 0;
        List<WebElement> initialSearchResultsPricesList = getDriver().findElements(By.cssSelector(".th-item-wrapper .th-price"));
        while (true) {
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
            currentResult.accept(currentPrice);
            prevPrice = currentPrice;
            i++;
        }
    }

    public void forEachButtonInAnalyticMenu(Consumer<WebElement> eachButtonsHoverOver) throws InterruptedException {
        for (Map.Entry<String, String> entry : buttonsWithHoverOvers.entrySet()) {
            TestHelper.moveToHiddenElement(getDriver(), getDriver().findElement(By.xpath(
                    String.format("//span[text()='%s']", entry.getKey()))), this.moreContainerBtn);
            eachButtonsHoverOver.accept(getDriver().findElement(By.xpath(String.format("//div[text()='%s']", entry.getValue()))));
        }
    }
}