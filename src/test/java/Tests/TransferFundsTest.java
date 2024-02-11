package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.URLs.*;

public class TransferFundsTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOMEPAGEURL);

        onlineBankingPage = new OnlineBankingPageMenu();
        transferFundsPage = new TransferFundsPage();

        logIn();

        onlineBankingPage.clickOnMenuButton();
        onlineBankingPage.clickOnMenuButtons("Transfer Funds");

    }
        @Test(priority = 10)
        public void userCanTransferFunds() {
            Assert.assertEquals(driver.getCurrentUrl(),TRANSFERFUNDSPAGE);

            selectAnyOptionFromDropDownMenu(transferFundsPage.fromAccountDropDown);
            selectAnyOptionFromDropDownMenu(transferFundsPage.toAccountDropDown);
            transferFundsPage.inputInAmount("300");
            transferFundsPage.inputDescription("Transfer Money");
            transferFundsPage.clickOnContinueButton();
            transferFundsPage.clickOnSubmitButton();

            Assert.assertFalse(elementIsDisplayed(transferFundsPage.submitButton));
            Assert.assertEquals(transferFundsPage.getTextFromMessage(),"You successfully submitted your transaction.");
            Assert.assertEquals(driver.getCurrentUrl(), TRANSFERFUNDCONFIRMATIONSPAGE);
        }

        @Test(priority = 20)
        public void userCanTransferFundsWithEmptyDescriptionField(){
            selectAnyOptionFromDropDownMenu(transferFundsPage.fromAccountDropDown);
            selectAnyOptionFromDropDownMenu(transferFundsPage.toAccountDropDown);
            transferFundsPage.inputInAmount("300");
            transferFundsPage.clickOnContinueButton();
            transferFundsPage.clickOnSubmitButton();

            Assert.assertEquals(transferFundsPage.getTextFromMessage(),"You successfully submitted your transaction.");
        }

        @Test(priority = 30)
        public void userCannotTransferFundsWithEmptyAmountField(){
            selectAnyOptionFromDropDownMenu(transferFundsPage.fromAccountDropDown);
            selectAnyOptionFromDropDownMenu(transferFundsPage.toAccountDropDown);
            transferFundsPage.inputDescription("Transfer Money");
            transferFundsPage.clickOnContinueButton();
            
            Assert.assertTrue(transferFundsPage.continueButton.isDisplayed());
            Assert.assertEquals(driver.getCurrentUrl(),TRANSFERFUNDSPAGE);
            Assert.assertFalse(elementIsDisplayed(transferFundsPage.transferMoneyMessage));

        }


        @AfterMethod
        public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
        }































}
