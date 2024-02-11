package Tests;

import Base.BaseTest;
import Base.RetryAnalyzer;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static Helpers.URLs.*;
import static org.openqa.selenium.Keys.ENTER;

public class HomepageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOMEPAGEURL);

        searchResultPage = new SearchResultPage();

        logIn();

    }
    @Test(priority = 10)
    public void userCanSearchTerms(){
        Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGEURL);

        homePage.inputSearchTerm(homePage.searchTerm);
        homePage.enterSearchTerm();

        Assert.assertEquals(driver.getCurrentUrl(),SEARCHRESULTSPAGE+homePage.searchTerm);
        Assert.assertEquals(searchResultPage.searchResultMessage(), "Search Results:");

    }
    @Test(priority = 20)
    public void userCanRequestForHelpRandomQuestion(){
        Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGEURL);

        homePage.clickOnSettingsButton();
        homePage.clickOnHelpButton();

        Assert.assertEquals(driver.getCurrentUrl(),HELPPAGE);

        helpPage.clickOnRandomQuestion();

        Assert.assertTrue(helpPage.answer.isDisplayed());
    }
    @Test(priority = 30)
    public void userCanRequestForHelpCertainQuestion(){
        Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGEURL);

        homePage.clickOnSettingsButton();
        homePage.clickOnHelpButton();

        Assert.assertEquals(driver.getCurrentUrl(),HELPPAGE);

        helpPage.clickOnQuestion(helpPage.question);

        Assert.assertTrue(elementIsPresent("From the top of the home page, click the Signin button.",helpPage.answers));
        Assert.assertTrue(helpPage.answer.isDisplayed());

    }


    @Test(priority = 40)
    public void clickingOnLogoRedirectsUserToHomepage(){
        Assert.assertEquals(driver.getCurrentUrl(),HOMEPAGEURL);

        homePage.clickOnSettingsButton();
        homePage.clickOnHelpButton();
        helpPage.clickOnRandomQuestion();

        Assert.assertNotEquals(driver.getCurrentUrl(),HOMEPAGEURL);

        homePage.clickOnLogo();

        Assert.assertEquals(driver.getCurrentUrl(),HOMEPAGEURL);

    }

    @Test(priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void clickingOnArrowChangesImage() throws InterruptedException {
        Assert.assertTrue(homePage.image.isDisplayed());
        homePage.clickOnArrow("‹");
        Thread.sleep(1000);

        Assert.assertFalse(homePage.image.isDisplayed());
        Assert.assertTrue(homePage.image1.isDisplayed());

        homePage.clickOnArrow("‹");
        Thread.sleep(1000);

        Assert.assertFalse(homePage.image1.isDisplayed());
        Assert.assertTrue(homePage.image2.isDisplayed());


    }
    @Test(priority = 60)
    public void clickingOnMenuBarRedirectsUserToAppropriatePage(){
        Assert.assertEquals(driver.getCurrentUrl(),HOMEPAGEURL);

        homePage.clickOnMenu("Checking Account Activity");

        Assert.assertEquals(driver.getCurrentUrl(),ACCOUNTACTIVITYPAGE);

    }

     @AfterMethod
    public void tearDown() {
          driver.manage().deleteAllCookies();
          driver.quit();
      }




}
