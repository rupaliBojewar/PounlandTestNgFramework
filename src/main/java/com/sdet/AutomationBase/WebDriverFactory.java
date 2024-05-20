package com.sdet.AutomationBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
WebDriver driver;
    public WebDriverFactory(WebDriver driver){
        this.driver=driver;

    }

    public WebDriver launchDriver(String browser){

        switch (browser) {
            case "chrome":
                driver=new ChromeDriver();
                break;
            case "firefox":
                driver=new FirefoxDriver();
                break;
            default:
                System.out.println("Provide right browser");
        }
        driver.manage().window().maximize();
        return driver;
    }

}
