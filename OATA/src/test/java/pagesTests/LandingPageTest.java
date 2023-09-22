package pagesTests;

import baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

public class LandingPageTest extends BaseTest {
    LandingPage landingPage;
    LoginPage loginPage;

    @BeforeClass
    @Parameters({"landingUrl", "browserName"})
    public void launchApplication(String landingUrl, String browserName) {
        super.launchApplication(landingUrl, browserName);
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
    }

    @Test
    @Parameters({"userName", "password"})
    public void verifyUserLogin(String userName, String password) {
        loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Test(dependsOnMethods = "verifyUserLogin")
    public void verifySortingItemsDropdown() {
        landingPage.selectSortFromDropDown();
    }

    @Test(dependsOnMethods = "verifySortingItemsDropdown")
    public void verifySorting(){
        landingPage.verifySorting();
    }

    @Test(dependsOnMethods = "verifySorting")
    public void verifyAddingItemToCart(){
        landingPage.addItemToCart("Sauce Labs Backpack");
        landingPage.addItemToCart("Sauce Labs Onesie");
    }

    @Test(dependsOnMethods = "verifyAddingItemToCart")
    public void verifyItemDetails(){
        landingPage.choseItem("Sauce Labs Backpack");
    }

    @Test(dependsOnMethods = "verifyItemDetails")
    public void verifyRemoveItem(){
        landingPage.removeItem();
    }

    @Test(dependsOnMethods = "verifyRemoveItem")
    public void countItems(){
        landingPage.countItemInCart(1);
    }
}
