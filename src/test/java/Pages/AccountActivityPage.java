package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


// Each class in the Pages package extends the BaseTest class

// Each page has its own class containing elements and methods specific to that page only
public class AccountActivityPage extends BaseTest {

    // When initializing elements using PageFactory, @FindBy annotation is used to locate elements on a web page

    // In order to access WebElement variables from a different class they need to be declared as public
    public AccountActivityPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(linkText = "Find Transactions")
    public WebElement findTransactionButton;
    @FindBy(linkText =  "Show Transactions")
    public WebElement showTransactionButton;
    @FindBy(id = "aa_accountId")
    public  WebElement accountFieldsDropDown;
    public String expectedText = "Brokerage";

    public String expectedURL = "http://zero.webappsecurity.com/bank/account-activity.html?accountId=6";
    public String selectedAccountFieldName = "Savings";

    @FindBy(css = ".table.table-condensed.table-hover")
    public WebElement transactionTable;
    @FindBy(id = "all_transactions_for_account")
    public WebElement table;
    @FindBy(id = "find_transactions_form")
    public WebElement transactionsForm;
    @FindBy(id = "aa_description")
    public WebElement descriptionField;
    @FindBy(id = "aa_fromDate")
    public WebElement fromDate;
    @FindBy(id = "aa_toDate")
    public WebElement toDate;
    @FindBy(id = "aa_fromAmount")
    public WebElement fromAmount;

    @FindBy(id = "aa_type")
    public WebElement type;



    @FindBy(css = "button[type = 'submit']")
    public WebElement findButton;
    @FindBy(id = "filtered_transactions_for_account")
    public WebElement findTransactionTable;



    //-------------------------------------------------------

    // I create methods based on the actions I need

    public void clickOnFindTransaction(){
        findTransactionButton.click();
    }
    public void clickOnShowTransaction(){
        showTransactionButton.click();
    }
    public void selectAccountField(String accountName){
        Select dropdown = new Select(accountFieldsDropDown);
        dropdown.selectByVisibleText(accountName);
    }
    public String selectedAccountFieldText(String accountName){
        Select dropdown = new Select(accountFieldsDropDown);
        dropdown.selectByVisibleText(accountName);
        return dropdown.getFirstSelectedOption().getText();

    }

    public String getTextFromTable(int targetRow, int targetColum){
        WebElement cell = table.findElement(By.xpath(".//tr[" + targetRow + "]/td[" + targetColum + "]"));
        return cell.getText();
    }
    public void inputDescriptionField(String description){
        //Before each input, it is necessary to perform a clear to ensure that any placeholder text is removed
        // from the text fields when entering data

        descriptionField.clear();
        descriptionField.sendKeys(description);
    }
    public void clickOnFindButton(){
        findButton.click();
    }

    public void inputDate(String date){
        fromDate.clear();
        fromDate.sendKeys(date);
    }
    public void inputToDate(String date){
        toDate.clear();
        toDate.sendKeys(date);
    }
    public void inputAmount(String amount){
        fromAmount.clear();
        fromAmount.sendKeys(amount);
    }


}
