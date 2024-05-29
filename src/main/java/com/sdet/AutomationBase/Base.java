package com.sdet.AutomationBase;

import com.sdet.pages.*;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class Base {
    public WebDriver driver;
    WebDriverFactory webDriverFactory;
    public HomePage homePage;
    public SignInPage signInPage;
    public WishListPage wishListPage;
    public MainPage mainPage;
    public CartPage cartPage;

//    @BeforeSuite
//    //Initialize the drivers
//    public void initialize() {
//        webDriverFactory = new WebDriverFactory(driver);
//        driver = webDriverFactory.launchDriver("chrome");
//    }

    @BeforeClass
    //Initialize the drivers
    public void initialize() {
        webDriverFactory = new WebDriverFactory(driver);
        driver = webDriverFactory.launchDriver("chrome");
        driver.get("https://www.poundland.co.uk/");
    }

//    @Parameters("url")
//    @BeforeTest
//    //Initialize the pages
//    public void beforeTest(String url) {
//        driver.get(url);

//        homePage = new HomePage(driver);
//        signInPage = new SignInPage(driver);
//        mainPage = new MainPage(driver);
//        wishListPage = new WishListPage(driver);
//        cartPage=new CartPage(driver);
    //}
    @BeforeMethod
    public void initializePages(){
        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        mainPage = new MainPage(driver);
        wishListPage = new WishListPage(driver);
        cartPage=new CartPage(driver);
    }

    public void getScreenshot(Method method) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir" )+ "/screenshot/" + method.getName() + ".png");
        FileUtils.copyFile(source, destination);
    }

    @AfterMethod
    public void takesScreenshotIfTestFail(ITestResult iTestResult, Method method) throws IOException {
        if (ITestResult.FAILURE == iTestResult.getStatus()) {
            getScreenshot(method);

            Allure.addAttachment(method.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }


    @AfterClass
    //quit the driver
    public void afterSuite() {
        if (driver != null)
        {driver.quit();}
    }
}
