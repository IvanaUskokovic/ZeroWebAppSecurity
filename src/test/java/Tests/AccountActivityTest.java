package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static Helpers.URLs.ACCOUNTACTIVITYPAGE;
import static Helpers.URLs.HOMEPAGEURL;

// The Test package contains test classes where tests are executed and results are verified
// Each Test class extends BaseTest class
public class AccountActivityTest extends BaseTest {

    // We create a pageSetup method with the @BeforeMethod annotation, indicating that this method will be
    // executed before each method with @Test annotation
    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver(); // Initializing a new WebDriver instance in @BeforeMethod because I want each test to run in a separate browser
        driver.manage().window().maximize();//this method is used to maximize the browser window during test automation
        driver.get(HOMEPAGEURL);
        //Implicit and explicit waits should be initialized after initializing driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        onlineBankingPage = new OnlineBankingPageMenu();
        accountActivityPage = new AccountActivityPage();

        logIn();

        onlineBankingPage.clickOnMenuButton();
        onlineBankingPage.clickOnMenuButtons("Account Activity");
    }

    // The action method names are given as if I am writing steps in a test case
    @Test(priority = 10) //By Adding priorities we specify the order in which methods will be executed
    //Priorities are set with 10 increment, if higher priority occur later in testing, priority of such
    //tests are placed between the two priorities
    public void userCanViewTransactions(){
        Assert.assertEquals(driver.getCurrentUrl(),ACCOUNTACTIVITYPAGE);

        accountActivityPage.selectAccountField(accountActivityPage.selectedAccountFieldName);

        Assert.assertTrue(accountActivityPage.transactionTable.isDisplayed());

        Assert.assertEquals(accountActivityPage.getTextFromTable(1,1),"2012-09-06");
        Assert.assertEquals(accountActivityPage.getTextFromTable(1,2),"ONLINE TRANSFER REF #UKKSDRQG6L");
        Assert.assertEquals(accountActivityPage.getTextFromTable(1,3),"984.3");
        Assert.assertEquals(accountActivityPage.getTextFromTable(2,1),"2012-09-05");
        Assert.assertEquals(accountActivityPage.getTextFromTable(2,2),"OFFICE SUPPLY");
        Assert.assertEquals(accountActivityPage.getTextFromTable(2,4),"50");
        Assert.assertEquals(accountActivityPage.getTextFromTable(3,1),"2012-09-01");
        Assert.assertEquals(accountActivityPage.getTextFromTable(3,2),"ONLINE TRANSFER REF #UKKSDRQG6L");
        Assert.assertEquals(accountActivityPage.getTextFromTable(3,3),"1000");
    }

    @Test(priority = 20)
    public void userCanFindTransactions() {
        accountActivityPage.clickOnFindTransaction();

        Assert.assertTrue(accountActivityPage.transactionsForm.isDisplayed());
        Assert.assertFalse(accountActivityPage.findTransactionTable.isDisplayed());

        accountActivityPage.inputDescriptionField("ONLINE TRANSFER REF #UKKSDRQG6L");
        accountActivityPage.inputDate("2024-01-01");
        accountActivityPage.inputToDate("2024-02-01");
        accountActivityPage.inputAmount("500");
        selectAnyOptionFromDropDownMenu(accountActivityPage.type);
        accountActivityPage.clickOnFindButton();

        wait.until(ExpectedConditions.visibilityOf(accountActivityPage.findTransactionTable));
        Assert.assertTrue(accountActivityPage.findTransactionTable.isDisplayed());

    }
    @Test(priority = 30)
    public void userCanFindTransactionsWithOnlyOneInput() {
        Assert.assertEquals(driver.getCurrentUrl(),ACCOUNTACTIVITYPAGE);

        accountActivityPage.clickOnFindTransaction();

        Assert.assertTrue(accountActivityPage.transactionsForm.isDisplayed());
        Assert.assertFalse(accountActivityPage.findTransactionTable.isDisplayed());

        accountActivityPage.inputDescriptionField("ONLINE TRANSFER REF #UKKSDRQG6L");
        accountActivityPage.clickOnFindButton();

        wait.until(ExpectedConditions.visibilityOf(accountActivityPage.findTransactionTable));
        Assert.assertTrue(accountActivityPage.findTransactionTable.isDisplayed());

    }



   @AfterMethod //The @AfterMethod is executed after each @Test annotation
    public void tearDown(){
       //Placing driver.quit() in the @AfterMethod ensures that the browser is closed after each test, preventing
       //any interference or influence from the previous test's state
        driver.manage().deleteAllCookies();
        driver.quit();
       //During the test development I'll comment out driver.quit() to keep the browser window open, in case of any failures
       //or to verify everything is correct. I'll uncomment driver.quit when I finalize tests
    }

}
