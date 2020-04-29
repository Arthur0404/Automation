package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.tophap.runner.SingleTest;
import pages.HomePage;
import pages.PricingPage;
import pages.PricingPage.PlanViewObject;
import pages.ProfilePage;
import pages.SignUpPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CreateFreeAccountTest extends SingleTest {

    // @Disabled
    @Test
    void createFreeAccount() throws InterruptedException {

        // open Sign up form from the Home page and sign up
        HomePage homePage = new HomePage(getDriver());
        homePage.openSignUpWindow();
        SignUpPage signUpPage = new SignUpPage(getDriver());
        final String EMAIL_RND = String.format("qualityA2019+TA" + Math.round(Math.random() * 1000) + "@gmail.com");
        signUpPage.signUp(signUpPage.NAME, EMAIL_RND, signUpPage.PASSWORD);

        // verify confirmation modal window is displayed and has correct information
        assertTrue(signUpPage.authentication.isDisplayed());
        assertEquals("Welcome to TopHap", signUpPage.welcomeHeading.getText());
        assertTrue(signUpPage.welcomeResendEmailBtn.isEnabled());
        signUpPage.welcomeOkBtn.click();
        signUpPage.closeWelcome(getDriver());

        // Close TopHap LiveChat window
        PricingPage pricingPage = new PricingPage(getDriver());
       // pricingPage.welcomeWindow.
       // pricingPage.closeFrameBtn.click();

        // verify that your subscription is free and you are connected to free plan

        List<WebElement> planElements = pricingPage.plansList;

       // assertTrue(planElements.size() > 0);
        for (WebElement element : planElements) {
            PlanViewObject plan = new PlanViewObject(element);
            if (plan.nameIs("Free")) assertTrue(plan.buttonIs("Cancel"));
            else assertTrue(plan.buttonIs("Get Started") || plan.buttonIs("Call Us"));
        }



        // verify that you are automatically logged in (avatar name on the screen equals name)
        pricingPage.openUserProfile(getDriver());
        ProfilePage profilePage = new ProfilePage(getDriver());
        assertEquals(EMAIL_RND, profilePage.emailField.getText());

////        // Go to My Account page
////        loginPage.openUserProfile(getDriver());
//
        // delete just created account from the system
        profilePage.deleteAccount();
        assertTrue(homePage.signUpButton.isDisplayed());
////        homePage.openUserProfile(getDriver());
////        getDriver().findElement(By.className("th-close-account-button")).click();
////        getDriver().findElement(By.xpath("//div[contains(@class,'th-alert-modal')]//button[contains(@class,'th-ok-action')]")).click();
////        assertTrue(getDriver().findElement(By.className("th-signup-button")).isDisplayed());

    }
}