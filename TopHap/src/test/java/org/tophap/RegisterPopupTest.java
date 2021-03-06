package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.tophap.runner.SingleTest;
import pages.HomePage;
import pages.ProfilePage;
import pages.SignUpPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterPopupTest extends SingleTest {

    @Disabled
    @Test
    void registerPopupTest() throws InterruptedException {

        HomePage homePage = new HomePage(getDriver());
        homePage.closeWelcome();

        // New user registration
        SignUpPage signUpPage = homePage.openSignUp();
        getDriver().switchTo().activeElement();
        final String EMAIL_RANDOM = String.format("%sTA@example.com", Math.round(Math.random() * 100));
        signUpPage.signUp(UserHelper.NAME, EMAIL_RANDOM, UserHelper.PASSWORD);

        // Authentication Popup should appear
        assertTrue(signUpPage.authentication.isDisplayed());

        // Notification that email should be verified
        assertTrue(signUpPage.emailConfirmationText().contains("We have sent an email to verify your account"));

        // Go to HomePage and close email confirmation failure PopUp window
        homePage.goToHome();
        homePage.closeEmailConfirmationFailureMsg();

        // Go to My Account page and deleting the account
        homePage.openUserProfile().deleteAccount();
        assertTrue(homePage.signUpMenuBtn.isDisplayed());
    }
}

