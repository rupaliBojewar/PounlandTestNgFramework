package com.sdet.pages;

import com.sdet.AutomationBase.SeleniumActions;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainPage {

    WebDriver driver;
    SeleniumActions seleniumActions;

    String itemName1;
    String itemName2;
    String itemName3;

    List<String> itemsToSelect;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
    }


    @FindBy(xpath = "//*[name()='g' and @id='Icon-/-Basket']")
    private WebElement cartBtn;
    @FindBy(xpath = "//div[@class='top-subnav']/descendant::a[text()='My Favourites']")
    private WebElement myWishList;

    @FindBy(xpath = "//div[@class='top-subnav']/descendant::a[text()='Log out']")
    private WebElement logout;
    @FindBy(xpath = "//a[@class='top-nav__link']")
    private WebElement myAccountBtn;
    @FindBy(xpath = "//li/a[text()='Garden']")
    private WebElement gardenPage;
    @FindBy(css = ".top-nav__item.customer-account")
    private WebElement signInField;
    @FindBy(xpath = "//a[text()='Wilson & Gregory Plant Pot Coco Liner (Pack of 2)']//ancestor::div[@class='product-item-info']/descendant::a[@href='#']")
    private WebElement itemToBeSelect;


    public void passingDataFromJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("src/TestData/itemDetails.json");
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        itemName1 = jsonObject.get("ItemOne").toString();
        System.out.println("item to select is :" + itemName1);
        itemName2 = jsonObject.get("ItemTwo").toString();
        itemName3 = jsonObject.get("ItemThree").toString();
    }

    public WebElement selectAnItem(String str) {

        //WebElement itemToSelect = driver.findElement(By.xpath("//a[text()='Wilson & Gregory Plant Pot Coco Liner (Pack of 2)']//ancestor::div[@class='product-item-info']/descendant::a[@href='#']"));

        WebElement itemToSelect = driver.findElement(By.xpath("//a[text()='" + str + "']//ancestor::div[@class='product-item-info']/descendant::a[@href='#']"));

        return itemToSelect;
    }



    public List<String> readItemsFromExcel(String sheetName) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/itemList.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
        itemsToSelect = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            Cell cell = xssfSheet.getRow(i).getCell(0);
            itemsToSelect.add(String.valueOf(cell));
        }
        return itemsToSelect;
    }

    public void addItemInWishlist() throws IOException {
        seleniumActions.waitForElementAndClick(gardenPage);
        List<String> itemToBeAdded = readItemsFromExcel("wishlistItem");

        for (String item : itemToBeAdded) {
            seleniumActions.waitForElementAndClick(selectAnItem(item));
        }
//        seleniumActions.waitForElementAndClick(selectAnItem(itemName1));
//        seleniumActions.waitForElementAndClick(selectAnItem(itemName2));
//        seleniumActions.waitForElementAndClick(selectAnItem(itemName3));
    }

    public WebElement addItem(String itemName) {
        WebElement itemToAdd = driver.findElement(By.xpath("//a[text()='" + itemName + "']/ancestor::div[@class='product-bottom-details']/descendant::button"));
        return itemToAdd;
    }

    public void addItemsInCart() throws IOException {
        seleniumActions.waitForElementAndClick(gardenPage);

        List<String> itemToBeAdded = readItemsFromExcel("cartItem");

        for (String item : itemToBeAdded) {
            seleniumActions.waitForElementAndClick(addItem(item));
        }
    }

    public void moveToWishListPage() {
        seleniumActions.waitForElementAndClick(myAccountBtn);
        seleniumActions.waitForElementAndClick(myWishList);
    }

    public void moveToCart() {
        Actions act =new Actions(driver);
        act.moveToElement(cartBtn).click().perform();
    //seleniumActions.waitForElementAndClick(cartBtn);
    }

    public void applicationLogOut(){
        seleniumActions.waitForElementAndClick(myAccountBtn);
        seleniumActions.waitForElementAndClick(logout);
    }

}
