package com.flipkart.stepdefinition;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class AmazonTv {
	static long startTime;
    static WebDriver driver;
  
    @BeforeClass
	public  static void browserlaunch() {
		System.out.println("launch method from before class");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com/");
		
	}
	@AfterClass
	public  static void browserquit() {
		System.out.println("quit the browser");
		//driver.close();
		//driver.quit();
	}
	@Before
	public void beforeMethod() {
		System.out.println("before method");
		long startTime = System.currentTimeMillis();
		
	}
	@After
	public void afterMethod() {
		System.out.println("after method");
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken for process:"+(endTime-startTime));
		
	}
	@Test
	public void method1() {
		//login page
		System.out.println(" method1 ");
		try {
		WebElement cross = driver.findElement(By.xpath("(//input[@type='submit'])[2]"));
		Assert.assertTrue(cross.isDisplayed());
		cross.click();}
		catch(Exception e){
			System.out.println("popup not came");
		}
		}	
	
	@Test
	public void method2() throws Exception {
		System.out.println(" method2 ");
		WebElement searchfield = driver.findElement(By.xpath("//input[@type='text']"));
		searchfield.sendKeys("television",Keys.ENTER);
		WebElement stock1 = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]"));
		String text1 = stock1.getText();
		System.out.println("stock1:"+text1);
		stock1.click();
		//write data from excel
		File f= new File("C:\\Users\\barga\\OneDrive\\Desktop\\Book77.xlsx");
		Workbook w=new XSSFWorkbook();
		Sheet sheet = w.createSheet("sample1");
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(text1);
		FileOutputStream f1=new FileOutputStream(f);
		w.write(f1);
	}
	
	@Test
	public void method3() throws Exception {
		System.out.println(" method3 ");
		
		//second page write data
		WebElement stock2 = driver.findElement(By.xpath("//span[@id='productTitle']"));
		String text2 = stock2.getText();
		System.out.println("stock2:"+text2);
		//excel
		File file=new File("C:\\Users\\barga\\OneDrive\\Desktop\\Book77.xlsx");
		FileInputStream f5=new FileInputStream(file);
		Workbook w1=new XSSFWorkbook(f5);
		Sheet sheet1 = w1.getSheet("sample1");
		Row row1 = sheet1.createRow(1);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue(text2);
		FileOutputStream f2=new FileOutputStream(file);
		w1.write(f2);
		
	
	}
	@Test
	public void method4() throws Exception {
		System.out.println(" method4 ");
		//excel read data for two rows
		File f4=new File("C:\\Users\\barga\\OneDrive\\Desktop\\Book77.xlsx");
		FileInputStream f3=new FileInputStream(f4);
		Workbook w2=new XSSFWorkbook(f3);
		Sheet sheet2 = w2.getSheet("sample1");
		Row row2 = sheet2.getRow(0);
	    Cell cell2 = row2.getCell(0);
	    System.out.println("first cell:"+cell2);
	    Row row3 = sheet2.getRow(1);
	    Cell cell3 = row3.getCell(0);
	    System.out.println("second cell:"+cell3);
	    //Assert.assertEquals(cell3, cell2);
	    if(cell3.equals(cell2)) {
	    	System.out.println("pass");
	   }else {
		   System.out.println("fail");
	   }
	} 
	@Test
	public void method5() throws Exception  {
		System.out.println(" method5 ");
		TakesScreenshot ts=(TakesScreenshot)driver;
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		File source=ts.getScreenshotAs(OutputType.FILE);
	//	File target =new File("C:\\Users\\barga\\eclipse-workspace\\Junit-project\\target\\flipkart.png");
		File target =new File(".//target//amazon.png");
	    FileUtils.copyFile(source, target);
	    
	}
	
	

}
