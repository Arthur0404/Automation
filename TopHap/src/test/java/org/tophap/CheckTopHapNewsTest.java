package org.tophap;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.runner.MultipleTest;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckTopHapNewsTest extends MultipleTest {

    private ProfilePage profilePage;

    @Order(1)
    @Test
    void login() {

        HomePage homePage = new HomePage(getDriver());

        LoginPage loginPage = homePage.openLogin();
        loginPage.login(UserHelper.EMAIL, UserHelper.PASSWORD);
        loginPage.closeEmailConfirmationFailureMsg();

        profilePage = loginPage.openUserProfile();
    }

    @Order(2)
    @Test
    void checkTopHapNewsTest() throws InterruptedException {

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", profilePage.checkboxTopHapNews);
        new WebDriverWait(getDriver(), 10).until(TestHelper.movingIsFinished(profilePage.checkboxTopHapNews));

        Thread.sleep(3000);

        String newBefore = getDriver().findElement(By.cssSelector("[aria-disabled]")).getAttribute("className");
        System.out.println(newBefore);

        String classNameBefore = getDriver().findElement(By.xpath("//span[@class='MuiIconButton-label']//parent::span")).getAttribute("className");
        boolean before = classNameBefore.contains("Mui-checked");
        System.out.println(classNameBefore);
        System.out.println(before);

        profilePage.checkboxTopHapNews.click();
        profilePage.submitBtn.click();
        getDriver().navigate().refresh();

        Thread.sleep(3000);

        String classNameAfter = getDriver().findElement(By.xpath("//span[@class='MuiIconButton-label']//parent::span")).getAttribute("className");
        boolean after = classNameAfter.contains("Mui-checked");
        System.out.println(classNameAfter);
        System.out.println(after);

        assertTrue(before ^ after);
    }

    @Order(3)
    @Test
    void logout() {
        profilePage.logout();
    }
}