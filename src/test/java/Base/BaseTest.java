package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
// During testing, I use IntelliJ IDEA, where I create a Maven project because it's easier to add libraries through the pom file

// Also, I use the Page Object Model (POM) because it is easier to maintain, facilitates better tracking of changes and allows
// defining elements in one centralized location

// TestNG is used to avoid repeating certain parts of code, utilizing the @Before, @After and @Test annotations for efficient setup, teardown
// and execution of test cases

// To avoid hardcoding, I utilize DataDrivenTesting, enabling easier modification/addition of test data and facilitating result comparison
// with expected outcomes

// I conduct testing on the Chrome browser, and if there's a need to test on other browsers, it is necessary to replace the driver and place it in the directory

// In the section below, I declare the driver and all the pages I intend to test
// They need to be declared as public. This allows them to be accessed from other classes
public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public ExcelReader excelReader;
    public HomePage homePage;
    public LoginPage loginPage;
    public ForgotPasswordPage forgotPasswordPage;
    public OnlineBankingPageMenu onlineBankingPage;
    public PayBillsPage payBillsPage;
    public TransferFundsPage transferFundsPage;
    public AccountActivityPage accountActivityPage;
    public AccountSummaryPage accountSummaryPage;
    public FeedbackPage feedbackPage;
    public FeedbackSentPage feedbackSentPage;
    public ForgottenPasswordSendPage forgottenPasswordSendPage;
    public SearchResultPage searchResultPage;
    public HelpPage helpPage;

    public FaqPage faqPage;
    public OnlineStatementsPage onlineStatementsPage;

    // @BeforeClass is executed only once before any test methods within the class
    // We set up the Chrome driver in the @BeforeClass method, ensuring it is configured before the execution of any test methods
    // For the same reason, we also create an Excel reader object in the @BeforeClass, ensuring that the Excel file can be read before the execution of any test methods

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        excelReader = new ExcelReader("TestData.xlsx");

    }
    // The BaseTest class serves as a container for storing auxiliary methods that will be shared among different tests
    public void logIn(){
        homePage = new HomePage();
        loginPage = new LoginPage();
        helpPage = new HelpPage();
        homePage.clickOnSignInButton();
        loginPage.inputUsername("username");
        loginPage.inputPassword("password");
        loginPage.clickOnSignInButton();
        driver.navigate().back();
    }

    public void selectAnyOptionFromDropDownMenu(WebElement element){
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());
        options.get(randomIndex).click();

    }
    public String getTextFromAlert(){
        Alert alert = driver.switchTo().alert();
        return alert.getText();

    }
    public void alertAccept(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean elementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception ignored) {
            return false;
        }

    }
    public boolean elementIsPresent(String element, List<WebElement> list){
        boolean elementIsPresent = false;
        for (WebElement pivot : list){
            if(pivot.getText().equals(element)){
                elementIsPresent = true;
                break;
            }
        }
        return elementIsPresent;
    }








}
