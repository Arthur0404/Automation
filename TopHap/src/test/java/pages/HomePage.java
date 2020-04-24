package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.TestHelper;

public class HomePage extends BasePage {

    public static final String URL = "https://next.tophap.com/";

    @FindBy(xpath = "//button[text()='Try for Free']")
    public WebElement tryForFreeButton;

    @FindBy(className = "th-signup-button")
    public WebElement signUpButton;

    @FindBy(xpath = "//button[text()='Try for Free']")
    public WebElement tryForFreeBtn;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root th-button th-close-button']")
    public WebElement closeBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    public void openSignUpWindow () {
        signUpButton.click();
    }

    public void tryForFreeStart(WebDriver driver) {
        tryForFreeBtn.click();
        super.closeWelcome(driver);
    }

    public void emailConfirmationFailureMsgClose(WebDriver driver) {
        WebElement waitForSignUpWindow = new WebDriverWait(driver, 10).until(TestHelper.movingIsFinished(
                By.xpath("//div[@class='Toastify__toast-container Toastify__toast-container--top-right th-notification-container']")));
        closeBtn.click();
    }
}
