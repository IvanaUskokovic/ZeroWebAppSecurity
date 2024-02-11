package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class HelpPage extends BaseTest {
    public HelpPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(tagName = "a")
    public List<WebElement> questions;
    @FindBy(className = "questions")
    public WebElement questions1;

    @FindBy(className = "span8")
    public WebElement answer;
    public String question = "How do I log into my account?";

    @FindBy(tagName = "li")
    public List<WebElement>answers;



//-------------------------------------------------------------
    public void clickOnQuestion(String name){
        for(int i = 0; i < questions.size(); i++){
            if(questions.get(i).getText().equals(name)){
                questions.get(i).click();
                break;
            }
        }
    }

    public void clickOnRandomQuestion(){
        List <WebElement> randomQuestion = questions1.findElements(By.tagName("a"));
        Random random = new Random();
        int randomIndex = random.nextInt(randomQuestion.size());
        randomQuestion.get(randomIndex).click();
    }











}
