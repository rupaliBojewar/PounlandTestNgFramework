package com.sdet.pages;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CalenderTest {

    @Test
    public void dateSelection() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        String dateToSelect = "Mon Jul 08 2024";

        driver.get("https://www.makemytrip.com/flights/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();

        Actions act = new Actions(driver);

        act.moveToElement(driver.findElement(By.xpath("//p[@data-cy='departureDate']"))).click().perform();

        //driver.findElement(By.xpath("//p[@data-cy='departureDate']")).click();

        WebElement nextMonthBtn = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));

        // WebElement date = driver.findElement(By.xpath("//div[@aria-label='" + dateToSelect + "']"));

        //List<WebElement> months=driver.findElements(By.xpath("//div[@class='DayPicker-Months']/descendant::div[@class='DayPicker-Caption']/child::div"));


//        for (WebElement w:months) {
//
//        }
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            if (driver.findElement(By.xpath("//div[@aria-label='" + dateToSelect + "']")).isDisplayed()) {
                nextMonthBtn.click();
            }
        }
        (driver.findElement(By.xpath("//div[@aria-label='" + dateToSelect + "']"))).click();

    }

    @Test
    public void passingDataFromJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("src/TestData/itemDetails.json");
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        String itemName1 = jsonObject.get("ItemOne").toString();
        String itemName2 = jsonObject.get("ItemTwo").toString();
        String itemName3 = jsonObject.get("ItemThree").toString();

        System.out.println(itemName1 + "    " + itemName2 + "    " + itemName3);
    }

    @Test
    public void readItemFromExcel() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/itemList.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("itemA");
        List<String> itemsToSelect = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            Cell cell = xssfSheet.getRow(i).getCell(0);
            itemsToSelect.add(String.valueOf(cell));
        }
        System.out.println(itemsToSelect);
    }
}
