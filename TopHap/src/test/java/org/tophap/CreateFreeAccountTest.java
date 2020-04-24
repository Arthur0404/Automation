package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tophap.runner.SingleTest;
import pages.HomePage;
import pages.ProfilePage;
import pages.SignUpPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateFreeAccountTest extends SingleTest {

   // @Disabled
    @Test
    void createFreeAccount() throws InterruptedException {

        // open Sign up form from the Home page and sign up
        HomePage homePage = new HomePage(getDriver());
        homePage.openSignUpWindow();
        SignUpPage signUpPage = new SignUpPage(getDriver());
        final String EMAIL_RND = String.format("qualityA2019+TA" + Math.round(Math.random() * 1000) + "@gmail.com");
        signUpPage.sighUp(UserHelper.NAME, EMAIL_RND, UserHelper.PASSWORD);

        // verify confirmation modal window is displayed and has correct information
        assertTrue(signUpPage.authentication.isDisplayed());
        assertEquals("Welcome to TopHap", signUpPage.welcomeHeading.getText());
        assertTrue(signUpPage.welcomeResendEmailBtn.isEnabled());
        signUpPage.welcomeOkBtn.click();
        TestHelper.closeWelcome(getDriver());

        // verify that your subscription is free and you are connected to free plan
        List<WebElement> buttons = getDriver().findElements(By.xpath("//div[contains(@class,'th-plan-info')]//button"));
        for (WebElement planButton : buttons) {
            String planBtnTxt = planButton.getText();
            assertTrue(planBtnTxt.equals("Get Started") || planBtnTxt.equals("Call Us"));
        }

        // verify that you are automatically logged in (avatar name on the screen equals name)
        UserHelper.openUserProfile(getDriver());
        ProfilePage profilePage = new ProfilePage(getDriver());
        assertEquals(EMAIL_RND, profilePage.emailField.getText());

        // delete just created account from the system
        profilePage.deleteAccount();
        assertTrue(homePage.signUpButton.isDisplayed());
    }
}