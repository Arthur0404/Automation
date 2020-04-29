package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//a[@class='th-signin-button']")
    public WebElement signInMenuButton;

    @FindBy(xpath = "//input[@placeholder='E-mail']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root th-button']")
    public WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(WebDriver driver, String email, String password) {
        signInMenuButton.click();
        driver.switchTo().activeElement();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
    }
}
