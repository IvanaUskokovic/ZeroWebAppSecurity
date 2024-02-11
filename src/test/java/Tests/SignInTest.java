package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.ForgotPasswordPage;
import Pages.ForgottenPasswordSendPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Helpers.URLs.*;

public class SignInTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOMEPAGEURL);


        homePage = new HomePage();
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        forgottenPasswordSendPage = new ForgottenPasswordSendPage();

        homePage.clickOnSignInButton();

    }

    @Test(priority = 10)
    public void userCanSignInWithValidCredentials(){
        Assert.assertEquals(driver.getCurrentUrl(),LOGINURL);

        String validUsername = excelReader.getStringData("LoginCredentials", 1, 0);
        String validPassword = excelReader.getStringData("LoginCredentials", 1, 1);

        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnSignInButton();
        driver.navigate().back();

        Assert.assertFalse(elementIsDisplayed(homePage.signInButton));
        Assert.assertNotEquals(driver.getCurrentUrl(),LOGINURL);
    }

     @Test(priority = 20)
     public void userCannotLogInWithInvalidUsername() {
       for (int i = 1; i <= excelReader.getLastRow("LoginCredentials"); i++) {
           String invalidUsername = excelReader.getStringData("LoginCredentials", i, 2);
           String validPassword = excelReader.getStringData("LoginCredentials", 1, 1);

           loginPage.inputUsername(invalidUsername);
           loginPage.inputPassword(validPassword);
           loginPage.clickOnSignInButton();

           Assert.assertTrue(loginPage.errorMessage.isDisplayed());
           Assert.assertEquals(loginPage.getErrorText(), "Login and/or password are wrong.");
           Assert.assertTrue(loginPage.signInButton.isDisplayed());
       }
   }
    @Test(priority = 30)
    public void userCannotLogInWithInvalidPassword() {

        for (int i = 1; i <= excelReader.getLastRow("LoginCredentials"); i++) {
            String validUsername = excelReader.getStringData("LoginCredentials", 1, 0);
            String invalidPassword = excelReader.getStringData("LoginCredentials", i, 3);

            loginPage.inputUsername(validUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnSignInButton();

            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
            Assert.assertEquals(loginPage.getErrorText(), "Login and/or password are wrong.");
            Assert.assertTrue(loginPage.signInButton.isDisplayed());
        }
    }

   @Test(priority = 40)
   public void userCannotLogInWithInvalidUsernameAndPassword() {
       for (int i = 1; i <= excelReader.getLastRow("LoginCredentials"); i++) {
           String invalidUsername = excelReader.getStringData("LoginCredentials", i, 2);
           String invalidPassword = excelReader.getStringData("LoginCredentials", i, 3);

           loginPage.inputUsername(invalidUsername);
           loginPage.inputPassword(invalidPassword);
           loginPage.clickOnSignInButton();

           Assert.assertTrue(loginPage.errorMessage.isDisplayed());
           Assert.assertEquals(loginPage.getErrorText(), "Login and/or password are wrong.");
       }
   }
    @Test(priority = 50)
    public void userCannotLogInWithEmptyUsernameAndPasswordFields() {

            loginPage.loginField.clear();
            loginPage.passwordField.clear();
            loginPage.clickOnSignInButton();

            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
            Assert.assertEquals(loginPage.getErrorText(), "Login and/or password are wrong.");
    }


    @Test (priority = 60)
    public void userCanLogout(){
        String validUsername = excelReader.getStringData("LoginCredentials", 1, 0);
        String validPassword = excelReader.getStringData("LoginCredentials", 1, 1);

        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnSignInButton();
        driver.navigate().back();

        homePage.clickOnUsernameButton("username");
        homePage.clickOnLogoutButton();

        Assert.assertTrue(homePage.signInButton.isDisplayed());
    }

    @Test(priority = 70)
    public void userCanRecoverPassword(){
        loginPage.clickOnForgotPassword();

        Assert.assertEquals(driver.getCurrentUrl(), FORGOTPASSWORDURL);
        Assert.assertEquals(forgotPasswordPage.getPasswordRecoveryMessageText(),"So you forgot your password? Give us your email address and we will email it to you.");

        String validEmail = excelReader.getStringData("LoginCredentials", 1, 4);

        forgotPasswordPage.inputEmail(validEmail);
        forgotPasswordPage.clickOnSendButton();

        Assert.assertEquals(driver.getCurrentUrl(), FORGOTTENPASSWORDSEND);
        Assert.assertEquals(forgottenPasswordSendPage.confirmationMessageText(),"Forgotten Password\nYour password will be sent to the following email: " + validEmail);
    }



   @AfterMethod
    public void tearDown() {
       driver.manage().deleteAllCookies();
       driver.quit();
   }


}
