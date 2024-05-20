package com.sdet.AutomationBase;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumActions {
    WebDriver driver;

    WebDriverWait wait;


    public SeleniumActions(WebDriver driver){
     this.driver=driver;
     wait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }

       //wait for element to be clickable and click
    public void waitForElementAndClick(WebElement element) {
        try {wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }catch (ElementClickInterceptedException e){
            Actions act =new Actions(driver);
            act.moveToElement(element);
        }catch(StaleElementReferenceException e){
            element.click();
        }
    }


//wait for the element to be clickable and send the data to the input field
    public void waitForElementAndSenKeys(WebElement element, String data) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(data);
    }


    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void typeValue(WebElement element, String data) {
        element.sendKeys(data);
    }

}
