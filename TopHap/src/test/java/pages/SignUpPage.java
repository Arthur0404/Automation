package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {

    @FindBy(xpath = "//div[@role='dialog']//h1")
    public WebElement signUpWindowHeading;

    @FindBy(xpath = "//input[@placeholder='Email']")
    public WebElement emailInputField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement passwordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

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
        this.emailInputField.sendKeys(email);
        this.passwordInputField.sendKeys(password);
        this.submitButton.click();
    }
}
