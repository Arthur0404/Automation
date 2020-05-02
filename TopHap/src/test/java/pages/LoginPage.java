package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.MainPage;

public class LoginPage extends MainPage {

    @FindBy(xpath = "//input[@placeholder='E-mail']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root th-button']")
    public WebElement submitBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {

        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitBtn.click();
    }
}
