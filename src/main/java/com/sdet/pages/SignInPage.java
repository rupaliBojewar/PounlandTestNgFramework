package com.sdet.pages;

import com.sdet.AutomationBase.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    WebDriver driver;
    SeleniumActions seleniumActions;


    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
    }


    @FindBy(css = "#firstname")
    private WebElement firstNameInputField;

    @FindBy(css = "#lastname")
    private WebElement lastNameInputField;
    @FindBy(css = "#email_address")
    private WebElement emailIdInputField;

    @FindBy(css = "#password.input-text")
    private WebElement passwordInputField;

    @FindBy(css = "#password-confirmation")
    private WebElement passwordConfirmationInputField;

    @FindBy(xpath = "//span[text()='Submit']")
    private WebElement submitBtn;


    public void createAnAccount() {

        seleniumActions.waitForElementAndSenKeys(firstNameInputField, "TestData");
        seleniumActions.waitForElementAndSenKeys(lastNameInputField, "TestData");
        seleniumActions.waitForElementAndSenKeys(emailIdInputField, "Test@yopmail.com");
        seleniumActions.waitForElementAndSenKeys(passwordInputField, "Test@1234");
        seleniumActions.waitForElementAndSenKeys(passwordConfirmationInputField, "Test@1234");
        seleniumActions.waitForElementAndClick(submitBtn);
    }

}
