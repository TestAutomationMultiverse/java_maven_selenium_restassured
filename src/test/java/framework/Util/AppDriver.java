package framework.Util;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class AppDriver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public WebDriver SeleniumDriverSetup(String browser) throws Exception {
        WebDriverManager.getInstance(browser).setup();
        
        WebDriver webDriver = getDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.manage().window().maximize();
        
        driver.set(webDriver);
        return driver.get();
    }
    
    private WebDriver getDriver(String browser) throws Exception {
        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments( "--remote-debugging-pipe" );
                return new FirefoxDriver(firefoxOptions);
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments( "--remote-debugging-pipe" );
                return new ChromeDriver(chromeOptions);
            default:
                throw new RuntimeException("Browser is not supported: " + browser);
        }
    }
}

