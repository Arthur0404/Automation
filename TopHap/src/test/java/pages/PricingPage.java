package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.base.MainPage;

import java.util.List;

public class PricingPage extends MainPage {

    @FindBys({
            @FindBy(xpath = "//div[contains(@class,'th-plan-info')]")
    })
    public List<WebElement> plansList;

    @FindBy (xpath = "//h1[@class='jsx-948257472 th-page-title']")
    public WebElement pageTitle;

    public PricingPage(WebDriver driver) { super(driver); }

    public static class PlanViewObject {

        public WebElement button;
        public WebElement name;

        public PlanViewObject(WebElement plan) {
            name = plan.findElement(By.className("th-plan-role"));
            button = plan.findElement(By.tagName("button"));
        }

        public boolean nameIs(String planName) { return name.getText().equals(planName);
        }

        public boolean buttonIs(String planName) {
            return button.getText().equals(planName);
        }
    }
}