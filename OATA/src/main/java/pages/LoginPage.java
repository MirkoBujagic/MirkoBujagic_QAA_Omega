package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "user-name")
    WebElement userNameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "login-button")
    WebElement loginButton;
    //fill in the username
    public void enterUserName(String userName){
        writeText(userNameField,userName);
    }

    //fill in the password
    public void enterPassword(String password){
        writeText(passwordField,password);
    }

    //Click button to login user
    public void clickLoginButton(){
        click(loginButton);
    }




}
