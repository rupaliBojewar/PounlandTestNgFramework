package com.sdet.pages;

import com.sdet.AutomationBase.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WishListPage {
    WebDriver driver;

    SeleniumActions seleniumActions;

    MainPage mainPage;


    public WishListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
        mainPage = new MainPage(driver);
    }


    @FindBy(xpath = "//td[@class='col-wish-details']/child::div[@class='product-det']/p/span[@class='product-name']")
    private List<WebElement> addedItemsInWishList;

    @FindBy(xpath = "//a[@title=\"Remove Item\"]")
    private List<WebElement> removBtns;

    public List<String> itemsFromWishList() {

        List<String> wishListItems = new ArrayList<>();

        for (WebElement item : addedItemsInWishList) {
            wishListItems.add(item.getText());
        }
        return wishListItems;
    }

    public void removeItemsFromWishList(){
        for (WebElement element:removBtns) {

            seleniumActions.waitForElementAndClick(element);

            if(removBtns.size()==0)
            break;
        }
    }

   }
