package com.sdet.pages;

import com.sdet.AutomationBase.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver driver;

    SeleniumActions seleniumActions;

    MainPage mainPage;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
        mainPage = new MainPage(driver);
    }


    @FindBy(xpath = "//strong[@class='product-item-name']/a")
    private List<WebElement> addedItemsInCart;

    @FindBy(xpath = "//ul[@class='actions']/descendant::span[text()=' Remove']")
    private List<WebElement> removeBtn;

    public List<String> itemsFromCart() {

        List<String> cartItems = new ArrayList<>();

        for (WebElement item : addedItemsInCart) {
            cartItems.add(item.getText());
        }
        return cartItems;
    }

    public void removeItemsFromCart(){
        for (WebElement element:removeBtn) {

            try
            {seleniumActions.waitForElementAndClick(element);
            }catch(StaleElementReferenceException se)
            {
                seleniumActions.waitForElementAndClick(driver.findElement(By.xpath("//ul[@class='actions']/descendant::span[text()=' Remove']")));
            }
            if(removeBtn.size()==0)
                break;
        }
    }
}
