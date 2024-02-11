package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OnlineBankingPageMenu extends BaseTest {

    public OnlineBankingPageMenu() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "onlineBankingMenu")
    public WebElement onlineBankingMenu;
    @FindBy(className = "headers")
    public List<WebElement>menuButons;
   //-------------------------------------------

   public void clickOnMenuButtons(String buttonName){
       for(int i = 0; i < menuButons.size(); i++){
           if(menuButons.get(i).getText().equals(buttonName)){
               menuButons.get(i).click();
               break;
           }
       }
   }

   public void clickOnMenuButton(){
       onlineBankingMenu.click();
   }

}
