package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class OnlineStatementsPage extends BaseTest {

    public OnlineStatementsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "os_accountId")
    public WebElement accountField;
    @FindBy(className = "board-header")
    public WebElement titleAccount;
    @FindBy(linkText = "2012")
    public WebElement statement;
    @FindBy(linkText = "Statement 01/10/12(57K)")
    public WebElement pdf;

    //----------------------------------------------------
    public void selectOptionFromDropDownMenu(WebElement element,int index){
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        options.get(index).click();

    }
    public void clickOnStatement(){
        statement.click();
    }
    public String getTextPdfTitle(){
        return pdf.getText();
    }



}