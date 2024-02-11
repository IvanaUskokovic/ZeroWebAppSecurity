package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "user_login")
    public WebElement loginField;

    @FindBy(id = "user_password")
    public WebElement passwordField;

    @FindBy(css = ".btn.btn-primary")
    public WebElement signInButton;

    @FindBy(css = ".alert.alert-error")
    public WebElement errorMessage;

    @FindBy(linkText = "Forgot your password ?")
    public WebElement forgottenPassword;


//-------------------------------------------------

    public void inputUsername(String username){
        loginField.clear();
        loginField.sendKeys(username);
    }
    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickOnSignInButton(){
        signInButton.click();
    }
    public void inputInvalidUsername(String invalidUsername){
        loginField.clear();
        loginField.sendKeys(invalidUsername);
    }
    public void inputInvalidPassword(String invalidPassword){
        passwordField.clear();
        passwordField.sendKeys(invalidPassword);
    }
    public String getErrorText(){
        return errorMessage.getText();
    }
    public void clickOnForgotPassword(){
        forgottenPassword.click();
    }






}
