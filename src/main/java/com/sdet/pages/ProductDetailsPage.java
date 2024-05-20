package com.sdet.pages;

import com.sdet.AutomationBase.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    WebDriver driver;
    SeleniumActions seleniumActions;

    String itemDetails;
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
    }

    @FindBy(xpath = "//span[text()='Add to Basket']")
    private WebElement addToBasketBtn;

    public void clickOnBasketBtn(){
        seleniumActions.waitForElementAndClick(addToBasketBtn);
    }

//    public String getItemDetails(){
//        String details=
//
//    }
}
