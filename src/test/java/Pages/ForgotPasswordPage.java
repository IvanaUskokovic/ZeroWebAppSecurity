package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends BaseTest {
    public ForgotPasswordPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user_email")
    public WebElement emailField;
    @FindBy(css = ".btn.btn-primary")
    public WebElement sendButton;

    @FindBy(tagName = "p")
    public WebElement passwordRecoveryMessage;
 //---------------------------------------------



 public void inputEmail(String validEmail){
     emailField.clear();
     emailField.sendKeys(validEmail);
 }
 public void clickOnSendButton(){
     sendButton.click();
 }
 public String getPasswordRecoveryMessageText(){
     return passwordRecoveryMessage.getText();
 }









}
