package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserHelper {

    public static final String EMAIL = "qualitya2019+ta1@gmail.com";
    public static final String PASSWORD = "TopHap";
    public static final String NAME = "TopHap";

    public static void tryForFreeFromHomePage(WebDriver driver) {
        driver.get("https://next.tophap.com/");
        driver.findElement(By.xpath("//button[text()='Try for Free']")).click();

        TestHelper.closeWelcome(driver);
    }

    public static void signUp(WebDriver driver, String name, String email, String pass) throws InterruptedException {
        // open Sign up form from the Home page
        driver.findElement(By.className("th-signup-button")).click();
        // verify you are on the Sign up form
        assertEquals("Sign Up for Free", driver.findElement(By.xpath("//div[@role='dialog']//h1")).getText());
        // populate Sign up form and Submit
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pass);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    private static class Login {

        @FindBy(xpath = "//a[@class='th-signin-button']")
        public WebElement signInMenuButton;

        @FindBy(xpath = "//input[@placeholder='E-mail']")
        public WebElement emailField;

        @FindBy(xpath = "//input[@placeholder='Password']")
        public WebElement passwordField;

        @FindBy(xpath = "//button[@class='MuiButtonBase-root th-button']")
        public WebElement submitButton;
    }

    public static void login(WebDriver driver, String email, String password) {
        Login login = new Login();
        PageFactory.initElements(driver, login);
        login.signInMenuButton.click();
        driver.switchTo().activeElement();
        login.emailField.sendKeys(email);
        login.passwordField.sendKeys(password);
        login.submitButton.click();
    }

    private static class Logout {
        @FindBy(xpath = "//div[@class='jsx-3275066862 th-menu-item th-avatar-wrapper']")
        public WebElement avatarMenuButton;

        @FindBy(xpath = "//li[text()='Sign Out']")
        public WebElement signOutButton;
    }

    public static void logout(WebDriver driver) {
        Logout logout = new Logout();
        PageFactory.initElements(driver, logout);
        TestHelper.moveToElement(driver, logout.avatarMenuButton);
        logout.signOutButton.click();
    }

    public static void openUserProfile(WebDriver driver) {
        TestHelper.moveToElement(driver, By.xpath( "//div[@class='jsx-3275066862 th-menu-item th-avatar-wrapper']"));
        driver.findElement(By.linkText("Account")).click();
    }

    public static void emailConfirmationFailureMsgClose(WebDriver driver) {
        WebElement waitForSignUpWindow = new WebDriverWait(driver, 10).until(TestHelper.movingIsFinished(
                By.xpath("//div[@class='Toastify__toast-container Toastify__toast-container--top-right th-notification-container']")));
            driver.findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-close-button']")).click();
    }
}