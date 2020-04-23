package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.tophap.runner.SingleTest;
import pages.HomePage;
import pages.ProfilePage;
import pages.SignUpPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterPopupTest extends SingleTest {

    @Disabled
    @Test
    void registerPopupTest() throws InterruptedException {
        // Constructor open HomePage URL
        HomePage homePage = new HomePage(getDriver());

        // Avoiding popup window
        TestHelper.closeWelcome(getDriver());

        // New user registration
        homePage.openSignUpWindow();
        getDriver().switchTo().activeElement();
        SignUpPage signUpPage = new SignUpPage(getDriver());
        final String EMAIL_RANDOM = String.format("%sTA@example.com", Math.round(Math.random() * 100));
        signUpPage.sighUp(UserHelper.NAME, EMAIL_RANDOM, UserHelper.PASSWORD);

        // Authentication Popup should appear
        assertTrue(signUpPage.authentication.isDisplayed());

        // Notification that email should be verified
        assertTrue(signUpPage.emailConfirmationText().contains("We have sent an email to verify your account"));

        // Go to HomePage and close email confirmation failure PopUp window
        ProfilePage profilePage = new ProfilePage(getDriver());
        profilePage.goToHomePage();
        UserHelper.emailConfirmationFailureMsgClose(getDriver());

        // Go to My Account page
        UserHelper.openUserProfile(getDriver());

        //deleting the account
        signUpPage.deleteAccount();
        assertTrue(homePage.signUpButton.isDisplayed());
    }
}

