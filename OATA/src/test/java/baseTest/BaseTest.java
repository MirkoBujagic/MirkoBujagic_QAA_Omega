package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    public WebDriver initializeDriver(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = getChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = getGeckoDriver();
        }
        return driver;
    }

    private WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        //options.addArguments("--disable-notifications");

        return new ChromeDriver(options);
    }

    private WebDriver getGeckoDriver() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public void launchApplication(String landingUrl, String browserName) {
        driver = initializeDriver(browserName);
        driver.get(landingUrl);
    }

    //@AfterClass
    public void teardown() {
        driver.quit();
    }
}
