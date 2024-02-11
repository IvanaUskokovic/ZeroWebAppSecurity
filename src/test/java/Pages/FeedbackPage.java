package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FeedbackPage extends BaseTest {
    public FeedbackPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "name")
    public WebElement nameField;
    @FindBy(id = "email")
    public WebElement emailField;
    @FindBy(id = "subject")
    public WebElement subjectField;
    @FindBy(id = "comment")
    public WebElement commentField;
    @FindBy(css = "input[type = 'submit']")
    public WebElement sendMessageButton;
    @FindBy(css = "input[type = 'reset']")
    public WebElement clearButton;
    @FindBy(id = "feedback")
    public WebElement feedbackButton;
    public String name = "John Doe";

    @FindBy(css = ".signin-controls.form-inputs")
    public WebElement emailForm;

    //--------------------------------------------------------------

    public void inputName(String name){
        nameField.clear();
        nameField.sendKeys(name);
    }
    public void inputEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void inputSubject(String subject){
        subjectField.clear();
        subjectField.sendKeys(subject);
    }
    public void inputComment(String comment){
        commentField.clear();
        commentField.sendKeys(comment);
    }
    public void clickOnSendMessageButton(){
        sendMessageButton.click();
    }
    public void clickOnClearButton(){
        clearButton.click();
    }
    public void clickOnFeedbackButton(){
        feedbackButton.click();
    }











}
