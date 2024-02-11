package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.URLs.*;

public class FeedbackTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOMEPAGEURL);

        feedbackPage = new FeedbackPage();
        feedbackSentPage = new FeedbackSentPage();
        faqPage = new FaqPage();

        logIn();

        feedbackPage.clickOnFeedbackButton();

    }

    @Test(priority = 10)
    public void userCanAskForFeedback() {
        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.emailForm.isDisplayed());

        feedbackPage.inputName(feedbackPage.name);
        feedbackPage.inputEmail("j.doe@gmail.com");
        feedbackPage.inputSubject("transaction history");
        feedbackPage.inputComment("How can I review my transaction history?");
        feedbackPage.clickOnSendMessageButton();

        Assert.assertNotEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertEquals(feedbackSentPage.feedbackMessageText(), "Feedback\n"  + "Thank you for your comments, " + feedbackPage.name +". They will be reviewed by our Customer Service staff and given the full attention that they deserve.");
        Assert.assertFalse(elementIsDisplayed(feedbackPage.sendMessageButton));
    }
    @Test(priority = 20)
    public void userCannotAskForFeedbackWithEmptyNameField() {
        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.emailForm.isDisplayed());

        feedbackPage.nameField.clear();
        feedbackPage.inputEmail("j.doe@gmail.com");
        feedbackPage.inputSubject("transaction history");
        feedbackPage.inputComment("How can I review my transaction history?");
        feedbackPage.clickOnSendMessageButton();

        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.sendMessageButton.isDisplayed());
    }
    @Test(priority = 30)
    public void userCannotAskForFeedbackWithEmptyEmailField() {
        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.emailForm.isDisplayed());

        feedbackPage.inputName(feedbackPage.name);
        feedbackPage.emailField.clear();
        feedbackPage.inputSubject("transaction history");
        feedbackPage.inputComment("How can I review my transaction history?");
        feedbackPage.clickOnSendMessageButton();

        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.sendMessageButton.isDisplayed());
    }
    @Test(priority = 40)
    public void userCannotAskForFeedbackWithEmptySubjectField() {
        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.emailForm.isDisplayed());

        feedbackPage.inputName(feedbackPage.name);
        feedbackPage.inputEmail("j.doe@gmail.com");
        feedbackPage.subjectField.clear();
        feedbackPage.inputComment("How can I review my transaction history?");
        feedbackPage.clickOnSendMessageButton();

        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.sendMessageButton.isDisplayed());
    }
    @Test(priority = 50)
    public void userCannotAskForFeedbackWithEmptyCommentField() {
        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.emailForm.isDisplayed());

        feedbackPage.inputName(feedbackPage.name);
        feedbackPage.inputEmail("j.doe@gmail.com");
        feedbackPage.inputSubject("transaction history");
        feedbackPage.commentField.clear();
        feedbackPage.clickOnSendMessageButton();

        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);
        Assert.assertTrue(feedbackPage.sendMessageButton.isDisplayed());
    }



    @Test(priority = 60)
    public void clickingOnFAQRedirectsUserToFAQPage(){
        Assert.assertEquals(driver.getCurrentUrl(), FEEDBACKPAGE);

        faqPage.clickOnFaqLink();

        Assert.assertEquals(driver.getCurrentUrl(), FAQPAGE);
        Assert.assertTrue(faqPage.faqTitle.isDisplayed());
        Assert.assertEquals(faqPage.faqGetText(), "Frequently Asked Questions");


    }

       @AfterMethod
        public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
        }

}
