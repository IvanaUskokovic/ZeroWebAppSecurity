package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import java.util.List;
import java.util.Random;

public class TransferFundsPage extends BaseTest {
    public TransferFundsPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "tf_amount")
    public WebElement amountField;
    @FindBy(id = "tf_description")
    public WebElement descriptionField;

    @FindBy(id = "tf_fromAccountId")
    public WebElement fromAccountDropDown;
    @FindBy(id = "tf_toAccountId")
    public WebElement toAccountDropDown;
    @FindBy(id = "btn_submit")
    public WebElement continueButton;
    @FindBy(css = ".alert.alert-success")
    public WebElement transferMoneyMessage;
    @FindBy(id = "btn_submit")
    public WebElement submitButton;



//---------------------------------------------------------



    public void inputInAmount(String amount){
        amountField.clear();
        amountField.sendKeys(amount);
    }
    public void inputDescription(String description){
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }
    public void clickOnSubmitButton(){
        submitButton.click();
    }
    public String getTextFromMessage(){
        return transferMoneyMessage.getText();
    }







}
