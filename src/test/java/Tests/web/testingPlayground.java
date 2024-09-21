package Tests.web;
import org.testng.annotations.Test;

import framework.Pages.HomePage;
import framework.Pages.TextInputPage;
import framework.Pages.VerifyTextPage;

public class testingPlayground extends BaseTestClass {


    @Test(priority = 1)
    public void clickTest() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage().
                clickOnClickButton();

        softAssert.assertEquals(driver.getCurrentUrl().contains("click"), true);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void playGroundButtonColorTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnPlayGroundButton();
        String expectedColor = System.getProperty("expectedColor");
        if (expectedColor != null) {
            softAssert.assertEquals(homePage.getPlaygroundButtonColour(), expectedColor, "button is clickable");
        }
        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void changeTextTest() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage()
                .clickOnTextInputButton()
                .clickOnTextFieldButton()
                .sendTextToInputField("smapleText")
                .clickOnChangeTextButton();

        TextInputPage textInputPage = new TextInputPage(driver);
        softAssert.assertEquals(textInputPage.getElementText(textInputPage.changeTextButton), "smapleText", "button is Changed");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void verifyTextTest() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage()
                .clickOnVerifyTextButton();

        VerifyTextPage verifyTextPage = new VerifyTextPage(driver);
        softAssert.assertEquals(verifyTextPage.getWelcomeText(), "Welcome UserName!", "Text is right");
        softAssert.assertAll();

    }

}
