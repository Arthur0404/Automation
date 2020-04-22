package org.tophap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.runner.MultipleTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeAccountInfoTest extends MultipleTest {

    private static final By LOGO_LOCATOR = By.xpath("//a[@class='th-logo']");

    @BeforeEach
    private void setUp() throws InterruptedException {

        // Open sign in form from the Home page and login
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        UserHelper.login(getDriver(), UserHelper.EMAIL, UserHelper.PASSWORD);

        // Close email confirmation failure PopUp window
        UserHelper.emailConfirmationFailureMsgClose(getDriver());

        // Go to My Account page
        UserHelper.openUserProfile(getDriver());
    }

    @Disabled
    @Test
    void changeAccountPhoneTest() throws InterruptedException {

        final String PHONE_NUMBER = String.valueOf(System.currentTimeMillis()).substring(0, 10);

        // Update phone number
        By phoneNumberLocator = By.xpath("//input[@placeholder='Phone Number']");
        WebElement phoneNumberField = getDriver().findElement(phoneNumberLocator);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(PHONE_NUMBER);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        // Verify that phone number is updated
        getDriver().findElement(LOGO_LOCATOR).click();
        UserHelper.openUserProfile(getDriver());
        phoneNumberField = getDriver().findElement(phoneNumberLocator);
        assertEquals(PHONE_NUMBER, phoneNumberField.getAttribute("value"));
    }

    @Disabled
    @Test
    void changeAccountNameTest() throws InterruptedException {

        final String NAME = String.format("TestTest%s", Math.round(Math.random()*100));

        // Update name
        By nameLocator = By.xpath("//input[@placeholder='Name']");
        WebElement nameField = getDriver().findElement(nameLocator);
        nameField.clear();
        nameField.sendKeys(NAME);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        // Verify that name is updated
        getDriver().findElement(LOGO_LOCATOR).click();
        UserHelper.openUserProfile(getDriver());
        nameField = getDriver().findElement(nameLocator);
        assertEquals(NAME, nameField.getAttribute("value"));
    }

    @Test
    void changeAccountPhotoTest() throws InterruptedException, AWTException {

        final String originalImageSource = getDriver().findElement(By.xpath("//button//img")).getAttribute("src");

        // Update photo
        getDriver().findElement(By.cssSelector(".th-button.th-avatar-wrapper")).click();
        Thread.sleep(1000);
        TestHelper.sendKeys("https://qph.fs.quoracdn.net/main-qimg-c3c2ffa630f2950492a0ef7ef06737cc\n");
        getDriver().findElement(By.cssSelector(".MuiButtonBase-root.th-button.th-select-button")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        WebElement wait = new WebDriverWait(getDriver(), 10)
                .until(TestHelper.movingIsFinished(By.xpath("//button//img[contains(@src,'https')]")));
        // Verify that there is a new photo on a profile
        assertNotEquals(originalImageSource, getDriver().findElement(By.xpath("//button//img")).getAttribute("src"));
    }

    @AfterEach
    public void setDown() {
        UserHelper.logout(getDriver());
    }
}