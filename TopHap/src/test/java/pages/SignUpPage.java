package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SignUpPage extends BasePage {

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
    public WebElement emailConfirmation;

    @FindBy(xpath = "//h1[@class='mt-5 mb-4 th-signup-success-title']")
    public WebElement welcomeHeading;

    @FindBy(xpath = "//button[text()='RESEND EMAIL']")
    public WebElement welcomeResendEmailBtn;

    @FindBy(xpath = "//button[text()='OK']")
    public WebElement welcomeOkBtn;

    @FindBy(className = "button.th-close-account-button") //todo: move to profile page
    public WebElement deleteAccountButton;

    @FindBy(xpath = "//button[contains(@class,'th-ok-action')]") //todo: move to profile page
    public WebElement getDeleteAccOKButton;

    @FindBy(xpath = "//div[contains(@class,'th-plan-info')]")
    public WebElement planName;

    @FindBys({
            @FindBy(xpath = "//input[@placeholder='Name' and @required]")
    })
    public List<WebElement> nameMandatoryIndicator;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public String getSignUpWindowHeading () {
        return signUpWindowHeading.getText();
    }

    public void signUpAttemptNoName (String email, String password) {
        this.emailField.sendKeys(email);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public void signUp( String name, String email, String password ){
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public String emailConfirmationText() { return emailConfirmation.getText(); }

    public String planNameText() {return planName.getText();}

    //todo: move to profile page
    public void deleteAccount() {
        deleteAccountButton.click();
        getDeleteAccOKButton.click();
    }
}
