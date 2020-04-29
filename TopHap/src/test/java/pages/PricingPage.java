package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class PricingPage extends BasePage {

    public PricingPage(WebDriver driver) { super(driver); }

    @FindBys({
            @FindBy(className = "//div[contains(@class,'th-plan-info')]")
    })
    public List<WebElement> plansList;

    public static class PlanViewObject {

        public PlanViewObject(WebElement plan) {
            name = plan.findElement(By.xpath(".th-plan-role")); ////div[contains(@class,'th-plan-info')]//h5")
            button = plan.findElement(By.xpath("//div[contains(@class,'th-plan-info')]//button"));
           // button = plan.findElement(By.xpath("//button[(@class='MuiButtonBase-root th-button th-select-button')]"));
        }

        public boolean nameIs(String planName) { return name.getText().equals(planName);
        }

        public boolean buttonIs(String planName) {
            return button.getText().equals(planName);
        }

        WebElement button;
        WebElement name;
    }

}
