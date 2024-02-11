package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPasswordSendPage extends BaseTest {
    public ForgottenPasswordSendPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "row")
    public WebElement confirmationMessage;
//-----------------------------------------------------------
    public String confirmationMessageText(){
       return confirmationMessage.getText();
    }








}

