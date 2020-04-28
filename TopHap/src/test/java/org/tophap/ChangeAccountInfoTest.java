package org.tophap;

import pages.HomePage;
import pages.LoginPage;
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
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(getDriver(), UserHelper.EMAIL, UserHelper.PASSWORD);

        // Close email confirmation failure PopUp window
        homePage.emailConfirmationFailureMsgClose(getDriver());

        // Go to My Account page
        loginPage.openUserProfile(getDriver());
    }

    @Order(2)
    @Test
    void changeAccountPhoneTest() throws InterruptedException {

        final String PHONE_NUMBER = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        ProfilePage profilePage = new ProfilePage(getDriver());

        // Update phone number
        profilePage.updatePhoneNumber(PHONE_NUMBER);

        // Go to HomePage and come back to ProfilePage
        profilePage.goToHomePage();
        profilePage.openUserProfile(getDriver());

        // Verify that phone number is updated
        assertEquals(PHONE_NUMBER, profilePage.getPhoneNumber());
    }

    @Order(3)
    @Test
    void changeAccountNameTest() throws InterruptedException {

        final String NAME = String.format("TestTest%s", Math.round(Math.random() * 100));
        ProfilePage profilePage = new ProfilePage(getDriver());

        // Update name
        profilePage.updateName(NAME);

        // Go to HomePage and come back to ProfilePage
        profilePage.goToHomePage();
        profilePage.openUserProfile(getDriver());

        // Verify that name is updated
        assertEquals(NAME, profilePage.getName());
    }

    @Order(4)
    @Test
    void changeUserNameTest() throws InterruptedException {

        final String USER_NAME = String.format("TestTest%s", Math.round(Math.random() * 100));
        ProfilePage profilePage = new ProfilePage(getDriver());

        // Update username
        profilePage.updateUserName(USER_NAME);

        // Go to HomePage and come back to ProfilePage
        profilePage.goToHomePage();
        profilePage.openUserProfile(getDriver());

        // Verify that username is updated
        assertEquals(USER_NAME, profilePage.getUserName());
    }

    @Order(5)
    @Test
    void changeEmailFailureTest() throws InterruptedException {

        final String NEW_EMAIL = "qualitya2019+ta2@gmail.com";
        ProfilePage profilePage = new ProfilePage(getDriver());

        // Attempt to update email
        profilePage.updateEmail(NEW_EMAIL);

        // Go to HomePage and come back to ProfilePage
        profilePage.goToHomePage();
        profilePage.openUserProfile(getDriver());

        // Verify that email is not updated
        assertEquals(UserHelper.EMAIL, profilePage.getEmail());
    }

    @Order(6)
    @Test
    void setDown() {
        new HomePage(getDriver()).logout(getDriver());
    }
}