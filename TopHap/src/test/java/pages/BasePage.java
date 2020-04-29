package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tophap.TestHelper;

import java.util.List;

public abstract class BasePage {

    public static final String EMAIL = "qualitya2019+ta1@gmail.com";
    public static final String PASSWORD = "TopHap";
    public static final String NAME = "TopHap";

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='jsx-3275066862 th-menu-item th-avatar-wrapper']") //div[@class='MuiAvatar-root MuiAvatar-circle th-avatar MuiAvatar-colorDefault']
    public WebElement avatarMenuButton;

    @FindBy(xpath = "//li[text()='Sign Out']")
    public WebElement signOutButton;

    @FindBy(linkText = "Account")
    public WebElement accountMenuButton;

    @FindBys({
            @FindBy(xpath = "//iframe[@title='Intercom Live Chat']")
           // @FindBy(xpath = "//div[@class='intercom-note intercom-duq38k e12dv51w0'])")
    })
    public List<WebElement> welcomeWindow;

   // @FindBy(xpath = "//span[@aria-label='Close']")
    @FindBy(xpath = "//iframe[@name='intercom-note-frame']")
    public WebElement closeFrameBtn;

    public void moveToElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void moveToElement(WebDriver driver, By locator) {
        moveToElement(driver, driver.findElement(locator));
    }

    public void moveToHiddenElement(WebDriver driver, By locator, By locatorDropDown) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement button = driver.findElement(locator);
        WebElement buttonDropDown = driver.findElement(locatorDropDown);
        if (driver.findElement(locator).isDisplayed()) {
            action.moveToElement(button).perform();
        } else {
            action.moveToElement(buttonDropDown).perform();
            moveToElement(driver,
                    new WebDriverWait(driver, 10).until(TestHelper.movingIsFinished(locator)));
        }
    }

    public void logout(WebDriver driver) {
        this.moveToElement(driver, avatarMenuButton);
        signOutButton.click();
    }

    public void openUserProfile(WebDriver driver) {
        this.moveToElement(driver, avatarMenuButton);
        accountMenuButton.click();
    }

    public void closeWelcome(WebDriver drive) {
        if (welcomeWindow.size() > 0) {
            drive.switchTo().frame(welcomeWindow.get(0));
            closeFrameBtn.click();
            drive.switchTo().defaultContent();
        }
    }

//    public void closeWelcome(WebDriver drive) {
//        List<WebElement> frames = drive.findElements(By.xpath("//iframe[@title='Intercom Live Chat']"));
//        if (frames.size() > 0) {
//            drive.switchTo().frame(frames.get(0));
//            drive.findElement(By.xpath("//span[@aria-label='Close']")).click();
//            drive.switchTo().defaultContent();
//        }
//    }
}
