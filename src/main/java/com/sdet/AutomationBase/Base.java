package com.sdet.AutomationBase;

import com.sdet.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class Base {
    public WebDriver driver;
    WebDriverFactory webDriverFactory;
    public HomePage homePage;
    public SignInPage signInPage;
    public WishListPage wishListPage;
    public MainPage mainPage;

    public CartPage cartPage;

    @BeforeSuite
    //Initialize the drivers
    public void initialize() {
        webDriverFactory = new WebDriverFactory(driver);
        driver = webDriverFactory.launchDriver("chrome");
    }

    @Parameters("url")
    @BeforeTest
    //Initialize the pages
    public void beforeTest(String url) {
        driver.get(url);
        //"https://www.poundland.co.uk/"
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        mainPage = new MainPage(driver);
        wishListPage = new WishListPage(driver);
        cartPage=new CartPage(driver);
    }

    @AfterSuite
    //quit the driver
    public void afterSuite() {
        driver.quit();
    }
}
