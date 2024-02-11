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

import static Helpers.URLs.*;

public class OnlineStatementsTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOMEPAGEURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        onlineBankingPage = new OnlineBankingPageMenu();
        onlineStatementsPage = new OnlineStatementsPage();

        logIn();

        onlineBankingPage.clickOnMenuButton();
        onlineBankingPage.clickOnMenuButtons("Online Statements");
    }
    @Test(priority = 10)
    public void userCanFindPdfStatements() {
        Assert.assertEquals(driver.getCurrentUrl(), ONLINESTATEMENTSPAGE);

        onlineStatementsPage.selectOptionFromDropDownMenu(onlineStatementsPage.accountField,1);

        Assert.assertTrue(onlineStatementsPage.titleAccount.isDisplayed());

        onlineStatementsPage.clickOnStatement();

        Assert.assertEquals(onlineStatementsPage.getTextPdfTitle(),"Statement 01/10/12(57K)");

    }
    @AfterMethod
        public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
        }

}
