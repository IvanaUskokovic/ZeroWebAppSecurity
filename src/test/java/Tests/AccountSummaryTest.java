package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static Helpers.URLs.ACCOUNTSUMMARYPAGE;
import static Helpers.URLs.HOMEPAGEURL;

public class AccountSummaryTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOMEPAGEURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        onlineBankingPage = new OnlineBankingPageMenu();
        accountActivityPage = new AccountActivityPage();
        accountSummaryPage = new AccountSummaryPage();

        logIn();

        onlineBankingPage.clickOnMenuButton();
        onlineBankingPage.clickOnMenuButtons("Account Summary");
    }
    @Test(priority = 10)
    public void userCanCheckAccountActivity(){
        Assert.assertEquals(driver.getCurrentUrl(),ACCOUNTSUMMARYPAGE);

        accountSummaryPage.selectAccount(accountSummaryPage.accountName);

        Assert.assertEquals(driver.getCurrentUrl(),accountActivityPage.expectedURL);
        Assert.assertTrue(accountActivityPage.accountFieldsDropDown.isDisplayed());
        Assert.assertEquals(accountActivityPage.selectedAccountFieldText("Brokerage"),accountActivityPage.expectedText);
        }


     @AfterMethod
     public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
     }



}
