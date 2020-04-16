package org.tophap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tophap.runner.MultipleTest;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeAccountInfoTest extends MultipleTest {

    private static final By USER_AVATAR_LOCATOR = By.className("UserAvatar--inner");
    private static final By ACCOUNT_LOCATOR = By.linkText("Account");
    private static final String PHONE_NUMBER = String.valueOf(System.currentTimeMillis()).substring(0, 10);
    private static final String NAME = String.format("TestTest%s", Math.round(Math.random()*100));

    @BeforeEach
    private void setUp() throws InterruptedException {

        // Open sign in form from the Home page and login
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        UserHelper.login(getDriver(), UserHelper.EMAIL, UserHelper.PASSWORD);
        Thread.sleep(2000);

        // Close email confirmation failure PopUp window
        List<WebElement> emailConfirmationFailureMsg = getDriver().findElements(
                By.xpath("//div[@class='Toastify__toast-container Toastify__toast-container--top-right th-notification-container']"));
        if (emailConfirmationFailureMsg.size() > 0) {
            getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-close-button']")).click();
        }

        // Go to My Account page
        TestHelper.moveToElement(getDriver(), USER_AVATAR_LOCATOR);
        getDriver().findElement(ACCOUNT_LOCATOR).click();
    }

    @Test
    void changeAccountPhoneTest() throws InterruptedException {

        // Update phone number
        By phoneNumberLocator = By.xpath("//input[@placeholder='Phone Number']");
        WebElement phoneNumberField = getDriver().findElement(phoneNumberLocator);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(PHONE_NUMBER);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        // Verify that phone number is updated
        getDriver().findElement(By.xpath("//a[@class='th-logo']")).click();
        TestHelper.moveToElement(getDriver(), USER_AVATAR_LOCATOR);
        getDriver().findElement(ACCOUNT_LOCATOR).click();
        phoneNumberField = getDriver().findElement(phoneNumberLocator);
        assertEquals(PHONE_NUMBER, phoneNumberField.getAttribute("value"));
    }

    @Test
    void changeAccountNameTest() throws InterruptedException {

        // Update name
        By nameLocator = By.xpath("//input[@placeholder='Name']");
        WebElement nameField = getDriver().findElement(nameLocator);
        nameField.clear();
        nameField.sendKeys(NAME);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        // Verify that name is updated
        getDriver().findElement(By.xpath("//a[@class='th-logo']")).click();
        TestHelper.moveToElement(getDriver(), USER_AVATAR_LOCATOR);
        getDriver().findElement(ACCOUNT_LOCATOR).click();

        nameField = getDriver().findElement(nameLocator);
        assertEquals(NAME, nameField.getAttribute("value"));
    }

    @AfterEach
    public void setDown() {
        UserHelper.logout(getDriver());
    }
}