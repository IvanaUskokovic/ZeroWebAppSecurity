package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.Keys.ENTER;

public class HomePage extends BaseTest {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "signin_button")
    public WebElement signInButton;

    @FindBy(className = "dropdown-toggle")
    public List<WebElement>buttons;

    @FindBy(id = "logout_link")
    public WebElement logoutButton;

    @FindBy (id = "searchTerm")
    public WebElement searchField;
    public String searchTerm = "account";
    @FindBy (className = "dropdown")
    public WebElement settingsButton;
    @FindBy(id = "help_link")
    public WebElement helpButton;
    @FindBy(className = "brand")
    public WebElement logo;
    @FindBy(tagName = "a")
    public List<WebElement>arrows;

    @FindBy(css = "img[src ='/resources/img/main_carousel_1.jpg']")
    public WebElement image;
    @FindBy(css = "img[src ='/resources/img/main_carousel_3.jpg']")
    public WebElement image1;
    @FindBy(css = "img[src ='/resources/img/main_carousel_2.jpg']")
    public WebElement image2;

    @FindBy(className = "headers")
    public List<WebElement>menuBar;





    //--------------------------------------------
    public void clickOnSignInButton(){
        signInButton.click();
    }

    public void clickOnUsernameButton(String buttonName){
        for (int i = 0; i < buttons.size(); i++){
            if(buttons.get(i).getText().equals(buttonName)){
                buttons.get(i).click();
                break;
            }
        }
    }


    public void clickOnLogoutButton(){
        logoutButton.click();
    }

    public void clickOnSettingsButton(){
        settingsButton.click();
    }
    public void clickOnHelpButton(){
        helpButton.click();
    }
    public void clickOnLogo(){
        logo.click();
    }
    public void inputSearchTerm(String term){
        searchField.clear();
        searchField.sendKeys(term);
    }
    public void enterSearchTerm(){
        searchField.sendKeys(ENTER);
    }

    public void clickOnArrow(String arrowSymbol){
        for (int i = 0; i < arrows.size(); i++){
            if(arrows.get(i).getText().equals(arrowSymbol)){
                arrows.get(i).click();
                break;
            }
        }
    }
    public void clickOnMenu(String name){
        for(WebElement element : menuBar){
            if(element.getText().equals(name)){
                element.click();
                break;
            }
        }
    }









}
