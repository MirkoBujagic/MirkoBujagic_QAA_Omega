package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LandingPage extends BasePage {
    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement sortDropdown;
    @FindBy(css = ".btn_secondary")
    WebElement removeButton;

    public void selectSortFromDropDown() {
        //wait page to load
        waitVisibility(sortDropdown);
        //select from dropdown
        Select dropdown = new Select(sortDropdown);
        dropdown.selectByIndex(2);
    }

    //split and parse to verify sorting
    public void verifySorting() {
        List<WebElement> listPrices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        double minPrice = 0;
        for (int i = 0; i < listPrices.size(); i++) {
            String priceWithCurrency = listPrices.get(i).getText();
            String[] pricesCurrency = priceWithCurrency.split("\\$");
            String textPrice = pricesCurrency[1];
            double price = Double.parseDouble(textPrice);
            //comparing the prices
            if (minPrice <= price) {
                minPrice = price;
            } else {
                System.out.println("The items are not well sorted");
                break;
            }
        }
    }

    public void addItemToCart(String productName) {
        List<WebElement> itemList = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        List<WebElement> addButtonList = driver.findElements(By.cssSelector(".btn_primary"));
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getText().contains(productName)) {
                click(addButtonList.get(i));
                break;
            }
        }
    }

    public void choseItem(String productName) {
        List<WebElement> itemList = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        List<WebElement> photoList = driver.findElements(By.xpath("//div[@class='inventory_item_img']"));
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getText().contains(productName)) {
                click(photoList.get(i));
                break;
            }
        }
    }

    public void removeItem() {
        click(removeButton);
    }

    public void countItemInCart(int expectedNumItems) {
        String countOfItem = driver.findElement(By.cssSelector(".fa-layers-counter")).getText();
        int numOfItem = Integer.parseInt(countOfItem);
        Assert.assertEquals(numOfItem,expectedNumItems);
    }


}
