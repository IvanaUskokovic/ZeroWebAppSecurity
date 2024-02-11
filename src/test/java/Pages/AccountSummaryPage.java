package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountSummaryPage extends BaseTest {

    public AccountSummaryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "a")
    public List<WebElement>accounts;
    public String accountName = "Brokerage";


 //------------------------------------------
    public void selectAccount(String accountName){
        for (int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getText().equals(accountName)){
                accounts.get(i).click();
                break;
            }
        }
    }













}
