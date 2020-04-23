package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {

    @FindBy(xpath = "//div[@role='dialog']//h1")
    public WebElement signUpWindowHeading;

    @FindBy(xpath = "//input[@placeholder='Name']")
    public WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='Email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(className = "th-authentication-modal")
    public WebElement authentication;

    @FindBy(xpath = "//div[@class='jsx-2476985185 th-authentication']")
    WebElement emailConfirmation;

    @FindBy(className = "th-close-account-button")
    WebElement deleteAccountButton;

    @FindBy(xpath = "//div[contains(@class,'th-alert-modal')]//button[contains(@class,'th-ok-action')]")
    WebElement getDeleteAccOKButton;

    @FindBys({
            @FindBy(xpath = "//input[@placeholder='Name' and @required]")
    })
    public List<WebElement> nameMandatoryIndicator;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getSignUpWindowHeading () {
        return signUpWindowHeading.getText();
    }

    public void signUpAttemptNoName (String email, String password) {
        this.emailField.sendKeys(email);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public void sighUp( String name, String email, String password ){
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public String emailConfirmationText() { return emailConfirmation.getText(); }

    public void deleteAccount() {
        deleteAccountButton.click();
        getDeleteAccOKButton.click();
    }
}
