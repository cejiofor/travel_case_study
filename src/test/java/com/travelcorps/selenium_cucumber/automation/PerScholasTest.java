package com.perscholas.selenium_cucumber_template.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PerScholasTest {
	private static WebDriver driver;
	private static JavascriptExecutor js;
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\dev\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
	}
	
	@AfterClass
	public static void tearDown() {
		driver.close();
	}
	
	@Test
	public void sbaPart1Test() throws InterruptedException {
		driver.get("https://perscholas.org/");
	    driver.manage().window().setSize(new Dimension(1296, 696));
	    driver.findElement(By.cssSelector(".dropdown > #mapToggle")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector("ul > li:nth-child(6) > a")).click();
	    driver.findElement(By.cssSelector(".navbar-subnav-right > li:nth-child(1) > a")).click();
	    js.executeScript("window.scrollTo(0,1018)");
	    driver.findElement(By.cssSelector(".col-md-4:nth-child(3) h3")).click();
	    
	    List<WebElement> eleList = driver.findElements(By.className("staff-department"));
	    
//	    System.out.println(eleList);
	    
	    List<String> staffNames = new ArrayList<>();
	    for(WebElement we : eleList) {
	    	staffNames.add(we.findElement(By.cssSelector("a > div > hgroup > h5")).getText());
	    }
	    
//	    System.out.println(staffNames);
	    
	    assertTrue(staffNames.contains("Robin Nadeau"));
	    assertFalse(staffNames.contains("Jake McIntosh"));
	}
}
