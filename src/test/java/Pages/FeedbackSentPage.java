package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FeedbackSentPage extends BaseTest {
    public FeedbackSentPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css= ".offset3.span6")
    public WebElement feedbackMessage;

    //---------------------------------------------
    public String feedbackMessageText(){
        return feedbackMessage.getText();
    }






}
