package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public static final String URL = "https://next.tophap.com/";

    @FindBy(xpath = "//button[text()='Try for Free']")
    public WebElement tryForFreeButton;

    @FindBy(className = "th-signup-button")
    public WebElement signUpButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    public void openSignUpWindow () {
        signUpButton.click();
    }
}
