package Tests;

import Base.BaseTest;
import Base.RetryAnalyzer;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.OnlineBankingPageMenu;
import Pages.PayBillsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import static Helpers.URLs.*;

public class PayBillsTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOMEPAGEURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        onlineBankingPage = new OnlineBankingPageMenu();
        payBillsPage = new PayBillsPage();

        logIn();

        onlineBankingPage.clickOnMenuButton();
        onlineBankingPage.clickOnMenuButtons("Pay Bills");
    }
    @Test(priority = 10)
    public void userCanPaySavedPayee(){
        Assert.assertEquals(driver.getCurrentUrl(),PAYBILLSPAGE);

        selectAnyOptionFromDropDownMenu(payBillsPage.payeeFieldDropDown);
        selectAnyOptionFromDropDownMenu(payBillsPage.accountFieldDropDown);

        payBillsPage.inputAmount("500");
        payBillsPage.inputDate("24-01-04");
        payBillsPage.inputDescription("Payed Saved Payee");
        payBillsPage.clickOnPayButton();

        Assert.assertNotEquals(driver.getCurrentUrl(),PAYBILLSPAGE);
        Assert.assertEquals(payBillsPage.getTextFromMessage(),"The payment was successfully submitted.");
    }
    @Test(priority = 20)
    public void userCanAddNewPayeeWithMandatoryFields() {
        payBillsPage.clickOnPayBillsMenu("Add New Payee");

        Assert.assertEquals(driver.getCurrentUrl(), PAYBILLSPAGE);

        for (int i = 1; i < excelReader.getLastRow("PayBills"); i++) {
            payBillsPage.clickOnPayBillsMenu("Add New Payee");

            String payeeName = excelReader.getStringData("PayBills", i, 1);
            String address = excelReader.getStringData("PayBills", i, 2);
            String account = String.valueOf (excelReader.getIntegerData("PayBills", i, 3));

            payBillsPage.inputNewPayeeName(payeeName);
            payBillsPage.inputNewPayeeAddress(address);
            payBillsPage.inputAccount(account);
            payBillsPage.clickOnAddButton();

            Assert.assertEquals(driver.getCurrentUrl(), PAYBILLSPAGEADDNEWPAEYE);
            Assert.assertFalse(elementIsDisplayed(payBillsPage.addButon));
            Assert.assertEquals(payBillsPage.getTextFromMessageAdd(), "The new payee " + payeeName + " was successfully created.");

            payBillsPage.clickOnPayBillsMenu("Add New Payee");

        }
    }

    @Test(priority = 30)
    public void userCanPurchaseForeignCurrency() {
        payBillsPage.clickOnPayBillsMenu("Purchase Foreign Currency");

        Assert.assertEquals(driver.getCurrentUrl(),PAYBILLSPAGE);
        Assert.assertTrue(payBillsPage.sellRateGetText().isEmpty());

        selectAnyOptionFromDropDownMenu(payBillsPage.currencyFieldDropDown);

        wait.until(ExpectedConditions.visibilityOf(payBillsPage.sellRateText));
        Assert.assertFalse(payBillsPage.sellRateGetText().isEmpty());

        payBillsPage.inputConversionAmount("500");

        Assert.assertFalse(payBillsPage.radioButton.isSelected());

        payBillsPage.clickOnRadioButton();
        payBillsPage.clickOnPurchaseButton();

        Assert.assertEquals(payBillsPage.getTextForeignCurrencyMessage(),"Foreign currency cash was successfully purchased.");
        Assert.assertFalse(elementIsDisplayed(payBillsPage.purchaseButton));
    }
    @Test(priority = 40)
    public void userCannotPurchaseForeignCurrencyWithEmptyAmountField()  {
        payBillsPage.clickOnPayBillsMenu("Purchase Foreign Currency");
        selectAnyOptionFromDropDownMenu(payBillsPage.currencyFieldDropDown);
        wait.until(ExpectedConditions.visibilityOf(payBillsPage.sellRateText));
        payBillsPage.clickOnRadioButton();
        payBillsPage.clickOnPurchaseButton();

        Assert.assertEquals(getTextFromAlert(),"Please, ensure that you have filled all the required fields with valid values.");
        alertAccept();
        Assert.assertTrue(payBillsPage.purchaseButton.isDisplayed());

    }
    @Test(priority = 50,retryAnalyzer = RetryAnalyzer.class)
    public void userCanInputOnlyNumericalValuesInAmountField(){

        for (int i = 1; i < excelReader.getLastRow("PayBills"); i++){
            String invalidAmount = excelReader.getStringData("PayBills", i, 0);
            payBillsPage.clickOnPayBillsMenu("Purchase Foreign Currency");
            selectAnyOptionFromDropDownMenu(payBillsPage.currencyFieldDropDown);
            wait.until(ExpectedConditions.visibilityOf(payBillsPage.sellRateText));
            payBillsPage.inputConversionAmount(invalidAmount);
            payBillsPage.clickOnRadioButton();
            payBillsPage.clickOnPurchaseButton();

            Assert.assertEquals(getTextFromAlert(),"Please, ensure that you have filled all the required fields with valid values.");

            alertAccept();
            payBillsPage.conversionAmountField.clear();

            Assert.assertTrue(payBillsPage.purchaseButton.isDisplayed());

        }
    }
    @Test(priority = 60)
    public void userCanCalculateCosts(){
        payBillsPage.clickOnPayBillsMenu("Purchase Foreign Currency");

        Assert.assertTrue(payBillsPage.getTextConversionAmount().isEmpty());

        payBillsPage.clickOnCertainCurrancyField();
        wait.until(ExpectedConditions.visibilityOf(payBillsPage.sellRateText));
        payBillsPage.inputConversionAmount("500");
        payBillsPage.clickOnRadioButton();
        payBillsPage.clickOnCalculateCostsButton();

        Assert.assertEquals(payBillsPage.getTextConversionAmount(),"360.70 euro (EUR) = 500.00 U.S. dollar (USD)");
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }



}
