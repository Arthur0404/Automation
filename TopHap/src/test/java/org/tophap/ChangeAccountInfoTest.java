package org.tophap;

import pages.ProfilePage;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.tophap.runner.MultipleTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeAccountInfoTest extends MultipleTest {

    private static final By LOGO_LOCATOR = By.xpath("//a[@class='th-logo']");

    @Order(1)
    @Test
    void setUp() throws InterruptedException {

        // Open sign in form from the Home page and login
        getDriver().get("https://next.tophap.com/");
        getDriver().manage().window().maximize();
        UserHelper.login(getDriver(), UserHelper.EMAIL, UserHelper.PASSWORD);

        // Close email confirmation failure PopUp window
        UserHelper.emailConfirmationFailureMsgClose(getDriver());

        // Go to My Account page
        UserHelper.openUserProfile(getDriver());
    }

    @Order(2)
    @Test
    void changeAccountPhoneTest() throws InterruptedException {

        final String PHONE_NUMBER = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        // Update phone number
        ProfilePage profilePage = new ProfilePage(getDriver());
        profilePage.populatePhoneNumberField(PHONE_NUMBER);
        profilePage.submitButtonClick();

        // Go to HomePage and come back to ProfilePage
        getDriver().findElement(LOGO_LOCATOR).click();
        UserHelper.openUserProfile(getDriver());

        // Verify that phone number is updated
        assertEquals(PHONE_NUMBER, profilePage.getPhoneNumber());
    }

    @Order(3)
    @Test
    void changeAccountNameTest() throws InterruptedException {

        final String NAME = String.format("TestTest%s", Math.round(Math.random()*100));

        // Update name
        ProfilePage profilePage = new ProfilePage(getDriver());
        profilePage.populateNameField(NAME);
        profilePage.submitButtonClick();

        // Go to HomePage and come back to ProfilePage
        getDriver().findElement(LOGO_LOCATOR).click();
        UserHelper.openUserProfile(getDriver());

        // Verify that name is updated
        assertEquals(NAME, profilePage.getName());
    }

    @Order(4)
    @Test
    void changeUserNameTest() throws InterruptedException {

        final String USER_NAME = String.format("TestTest%s", Math.round(Math.random()*100));

        // Update username
        ProfilePage profilePage = new ProfilePage(getDriver());
        profilePage.populateUserNameField(USER_NAME);
        profilePage.submitButtonClick();

        // Go to HomePage and come back to ProfilePage
        getDriver().findElement(LOGO_LOCATOR).click();
        UserHelper.openUserProfile(getDriver());

        // Verify that username is updated
        assertEquals(USER_NAME, profilePage.getUserName());
    }

    @Order(5)
    @Test
  void setDown() {
        UserHelper.logout(getDriver());
    }
}