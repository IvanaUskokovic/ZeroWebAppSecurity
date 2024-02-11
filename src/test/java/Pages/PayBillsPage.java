package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PayBillsPage extends BaseTest {

    public PayBillsPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".ui-state-default.ui-corner-top")
    public List<WebElement> payBillsMenuButtons;
    @FindBy(id = "sp_payee")
    public WebElement payeeFieldDropDown;
    @FindBy(id = "sp_account")
    public WebElement accountFieldDropDown;
    @FindBy(id = "sp_amount")
    public WebElement amountField;
    @FindBy(id = "sp_date")
    public WebElement dateField;
    @FindBy(id = "sp_description")
    public WebElement descriptionField;
    @FindBy(id = "pay_saved_payees")
    public WebElement payButton;
    @FindBy(id = "alert_content")
    public WebElement messageTitle;
    @FindBy(id = "np_new_payee_name")
    public WebElement payeeNameField;
    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddressField;
    @FindBy(id = "np_new_payee_account")
    public WebElement accountField;
    @FindBy(id = "add_new_payee")
    public WebElement addButon;
    @FindBy(id = "alert_content")
    public WebElement addTitle;

    @FindBy(id = "pc_currency")
    public WebElement currencyFieldDropDown;
    @FindBy(id = "sp_sell_rate")
    public WebElement sellRateText;
    @FindBy(id = "pc_amount")
    public WebElement conversionAmountField;
    @FindBy(id = "pc_inDollars_true")
    public WebElement radioButton;
    @FindBy(id = "purchase_cash")
    public WebElement purchaseButton;
    @FindBy(id = "alert_content")
    public WebElement foreignCurrencyMessage;
    @FindBy(id = "pc_calculate_costs")
    public WebElement calculateCostsButton;
    @FindBy(id = "pc_conversion_amount")
    public WebElement conversionalAmountText;
    @FindBy(css = "option[value = 'EUR']")
    public WebElement certainCurrencyField;




    //---------------------------------------------------

    public void clickOnPayBillsMenu(String name){
        for (int i = 0; i < payBillsMenuButtons.size(); i++){
            if(payBillsMenuButtons.get(i).getText().equals(name)){
                payBillsMenuButtons.get(i).click();
                break;
            }
        }
    }
    public void inputAmount(String amount){
        amountField.clear();
        amountField.sendKeys(amount);

    }
    public void inputDate(String date){
        dateField.clear();
        dateField.sendKeys(date);
    }
    public void inputDescription(String description){
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }
    public void clickOnPayButton(){
        payButton.click();
    }
    public String getTextFromMessage(){
        return messageTitle.getText();

    }
    public void inputNewPayeeName(String payeeName){
        payeeNameField.clear();
        payeeNameField.sendKeys(payeeName);
    }
    public void inputNewPayeeAddress(String payeeAddress){
        payeeAddressField.clear();
        payeeAddressField.sendKeys(payeeAddress);
    }
    public void inputAccount(String account){
        accountField.clear();
        accountField.sendKeys(account);
    }
    public void clickOnAddButton(){
        addButon.click();
    }
    public String getTextFromMessageAdd(){
        return addTitle.getText();

    }
    public String sellRateGetText(){
        return sellRateText.getText();
    }
    public void inputConversionAmount(String amount){
        conversionAmountField.sendKeys(amount);
    }

    public void clickOnRadioButton(){
        radioButton.click();
    }
    public void clickOnPurchaseButton(){
        purchaseButton.click();
    }

    public String getTextForeignCurrencyMessage(){
        return foreignCurrencyMessage.getText();
    }
    public void clickOnCalculateCostsButton(){
        calculateCostsButton.click();
    }
    public String getTextConversionAmount() {
        return conversionalAmountText.getText();
    }

    public void clickOnCertainCurrancyField(){
        certainCurrencyField.click();
    }






















}
