package pagesTests;

import baseTest.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
      LoginPage loginPage;

      @BeforeMethod
      @Parameters({"landingUrl", "browserName"})
      public void launchApplication(String landingUrl, String browserName){
          super.launchApplication(landingUrl,browserName);
          loginPage = new LoginPage(driver);
      }
    @Test
    @Parameters({"userName","password"})
    public void verifyUserLogin(String userName, String password){
        loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
}
