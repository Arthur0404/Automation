package org.tophap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.tophap.runner.MultipleTest;
import pages.*;

import static pages.PricingPage.PlanViewObject;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateFreeAccountTest extends MultipleTest {

    private PricingPage pricingPage;;
    private HomePage homePage;

    @Disabled
    @Order(1)
    @Test
    void signUpFreeTest() {

        homePage = new HomePage(getDriver());
        SignUpPage signUpPage = homePage.openSignUp();
        final String EMAIL_RND = String.format("qualityA2019+TA%s", Math.round(Math.random() * 1000) + "@gmail.com");
        signUpPage.signUp(UserHelper.NAME, EMAIL_RND, UserHelper.PASSWORD);

        assertTrue(signUpPage.authentication.isDisplayed());
        assertEquals("Welcome to TopHap", signUpPage.welcomeHeading.getText());
        assertTrue(signUpPage.welcomeResendEmailBtn.isEnabled());

        signUpPage.welcomeOkBtn.click();
        signUpPage.closeWelcome();
    }

    @Disabled
    @Order(2)
    @Test
    void freePlanSelected() {

        pricingPage = new PricingPage(getDriver());
        pricingPage.plansList.forEach(webElement -> {
            PlanViewObject plan = new PlanViewObject(webElement);

            if (plan.nameIs("Free")) assertTrue(plan.buttonIs("Cancel"));
            else assertTrue(plan.buttonIs("Get Started") || plan.buttonIs("Call Us"));
        });
    }

    @Disabled
    @Order(3)
    @Test
    void deleteAccount() {

        pricingPage.openUserProfile().deleteAccount();
        assertTrue(homePage.signUpMenuBtn.isDisplayed());
    }
}
