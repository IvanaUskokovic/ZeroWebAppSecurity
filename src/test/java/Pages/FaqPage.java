package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FaqPage extends BaseTest {

    public FaqPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "faq-link")
    public WebElement faqLink;
    @FindBy(className = "page-header")
    public WebElement faqTitle;

  //----------------------------------------------------------------

    public void clickOnFaqLink(){
        faqLink.click();
    }
    public String faqGetText(){
        return faqTitle.getText();
    }


}
