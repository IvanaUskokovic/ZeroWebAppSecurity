package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BaseTest {

    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(tagName = "h2")
    public WebElement searchResultsText;
  //-------------------------------------------

  public String searchResultMessage(){
    return  searchResultsText.getText();
  }





}
