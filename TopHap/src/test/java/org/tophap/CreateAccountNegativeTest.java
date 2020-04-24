package org.tophap;

import pages.HomePage;
import pages.SignUpPage;
import org.junit.jupiter.api.Test;
import org.tophap.runner.SingleTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateAccountNegativeTest extends SingleTest {

    @Test
    void attemptToCreateAccountWithFailedData() throws InterruptedException {

        // open Sign up form from the Home page
        HomePage homePage = new HomePage(getDriver());
        homePage.openSignUpWindow();

        // verify you are on the Sign up form
        SignUpPage signUpPage = new SignUpPage(getDriver());
        assertEquals("Sign Up for Free", signUpPage.getSignUpWindowHeading());

        // populate Sign up form (Name field leave empty) and Submit
        signUpPage.signUpAttemptNoName("qualityA2019+TA" + Math.round(Math.random()*1000) + "@gmail.com", homePage.PASSWORD);

        // verify the Name field is mandatory and that you are left of the Sign up form
        assertTrue(signUpPage.nameMandatoryIndicator.size() == 1);
        assertEquals("Sign Up for Free", signUpPage.getSignUpWindowHeading());
    }
}