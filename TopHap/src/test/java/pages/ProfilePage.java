package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    private static final String URL = "https://next.tophap.com/settings/profile";

    @FindBy(css = ".th-button.th-avatar-wrapper")
    public WebElement avatarPhoto;

    @FindBy(xpath = "//input[@placeholder='Name']")
    public WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='Username']")
    public WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder='Phone Number']")
    public WebElement phoneNumberField;

    @FindBy(xpath = "//input[@placeholder='Email']")
    public WebElement emailField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//a[@class='th-logo']")
    public WebElement siteLogo;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void submitButtonClick() {
        submitButton.click();
    }

    public void openProfilePage(WebDriver driver) {
        driver.get(URL);
    }

    public void goToHomePage() {
        siteLogo.click();
    }

    public void updateName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
        submitButton.click();
    }

    public void updateUserName(String userName) {
        usernameField.clear();
        usernameField.sendKeys(userName);
        submitButton.click();
    }

    public void updatePhoneNumber(String phoneNumber) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
        submitButton.click();
    }

    public void updateEmail(String email) {
        emailField.sendKeys(email);
        submitButton.click();
    }

    public String getName() {
        return nameField.getAttribute("value");
    }

    public String getUserName() {
        return usernameField.getAttribute("value");
    }

    public String getEmail() {
        return emailField.getAttribute("value");
    }

    public String getPhoneNumber() {
        return phoneNumberField.getAttribute("value");
    }


}

