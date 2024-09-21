package framework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyTextPage extends Page {

    private final By welcomeText = By.xpath("//div[@class='bg-primary']//span[@class='badge-secondary']");

    public VerifyTextPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns the welcome text from the page
     *
     * @return the welcome text as a string
     */
    public String getWelcomeText() {
        return getElementText(welcomeText);
    }
}

