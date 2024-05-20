package com.sdet.pages;

import com.sdet.AutomationBase.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
    WebDriver driver;
    SeleniumActions seleniumActions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);

    }

    @FindBy(xpath = "//button[text()='Accept All Cookies']")
    private WebElement acceptCookiesBtn;

    @FindBy(css = ".top-nav__item.customer-account")
    private WebElement signInField;

    @FindBy(xpath = "//a[text()='Create an Account']")
    private WebElement createAnAccountBtn;

    @FindBy(xpath = "//div[@data-testid='om-overlays-close']")
    private WebElement closeBtn;

    @FindBy(css = "#email")
    private WebElement emailIdInputField;

    @FindBy(css = "#pass")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button[@id='send3']")
    private WebElement signInBtn;


    public void switchToSignInPage() {
        seleniumActions.clickOnElement(acceptCookiesBtn);
        seleniumActions.clickOnElement(signInField);
        seleniumActions.clickOnElement(createAnAccountBtn);
        if (closeBtn.isDisplayed()) {
            closeBtn.click();
        }
    }

    public void signInAccount() {
        //driver.navigate().refresh();
        seleniumActions.clickOnElement(acceptCookiesBtn);
        seleniumActions.waitForElementAndClick(signInField);
        seleniumActions.waitForElementAndSenKeys(emailIdInputField, "Test@yopmail.com");
        seleniumActions.waitForElementAndSenKeys(passwordInputField, "Test@1234");
        seleniumActions.waitForElementAndClick(signInBtn);
    }
}
