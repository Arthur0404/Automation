package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tophap.runner.MultipleTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeAccountInfoTest extends MultipleTest {
    @Test
    void changeAccountPhoneTest() throws InterruptedException {

        final String phoneNumber = String.valueOf(System.currentTimeMillis()).substring(0, 10);

        // Open sign in form from the Home page and login
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        UserHelper.login(getDriver(), UserHelper.EMAIL, UserHelper.PASSWORD);

        // Close email confirmation failure PopUp window
        List<WebElement> emailConfirmationFailureMsg = getDriver().findElements(
                By.xpath("//div[@class='Toastify__toast-container Toastify__toast-container--top-right th-notification-container']"));
        if (emailConfirmationFailureMsg.size() > 0) {
            getDriver().findElement(By.xpath("//button[@class='MuiButtonBase-root th-button th-close-button']")).click();
        }

        // Go to My Account page
        By userAvatarLocator = By.className("UserAvatar--inner");
        TestHelper.moveToElement(getDriver(), userAvatarLocator);

        By accountLocator = By.linkText("Account");
        getDriver().findElement(accountLocator).click();

        // Update phone number
        By phoneNumberLocator = By.xpath("//input[@placeholder='Phone Number']");
        WebElement phoneNumberField = getDriver().findElement(phoneNumberLocator);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        // Verify that phone number is updated
        getDriver().findElement(By.xpath("//a[@class='th-logo']")).click();
        TestHelper.moveToElement(getDriver(), userAvatarLocator);
        getDriver().findElement(accountLocator).click();

        phoneNumberField = getDriver().findElement(phoneNumberLocator);
        assertEquals(phoneNumber, phoneNumberField.getAttribute("value"));
    }
}