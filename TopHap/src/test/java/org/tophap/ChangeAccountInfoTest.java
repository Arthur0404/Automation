package org.tophap;

import com.sun.glass.events.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.runner.MultipleTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        getDriver().findElement(By.cssSelector(".th-button.th-avatar-wrapper")).click();
        //WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        //wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_A+1);
        robot.keyRelease(KeyEvent.VK_A+1);
//        WebElement frame = getDriver().switchTo().activeElement();
       // frame.sendKeys("C:\\Users\\anna_\\Downloads\\test.jpg");
    }

    @AfterEach
    public void setDown() {
        UserHelper.logout(getDriver());
    }
}