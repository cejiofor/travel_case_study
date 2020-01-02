package com.travelcorps.selenium_cucumber.step_definitions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class PopularSessionStepDef {
	private WebDriver driver;
	private Map<String, Object> vars;
	private JavascriptExecutor js;
	
	
    @Given("^the user navigates to the store page$")
    public void the_user_navigates_to_the_store_page() throws Throwable {
    	System.out.println("User navigates to store page");
    	
    	System.setProperty("webdriver.chrome.driver", 
				"C:\\dev\\chromedriver\\chromedriver.exe");
    	driver = new ChromeDriver();
    	js = (JavascriptExecutor) driver;
    	
    	driver.get("http://automationpractice.com/index.php");
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	String actual = driver.getTitle();
    	String expected = "My Store";
    	
    	assertEquals(actual, expected);
    }

    @When("^the user clicks on an item$")
    public void the_user_clicks_on_a_category() throws Throwable {
    	System.out.println("User clicks on an item");
    	
    	Actions action = new Actions(driver);
    	
    	WebElement product1 = driver.findElement(By.cssSelector("#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line > div > div.left-block > div > a.product_img_link > img"));
    	action.moveToElement(product1).perform();
    	driver.findElement(By.cssSelector("#homefeatured > .ajax_block_product:nth-child(1) .button:nth-child(1) > span")).click();
        driver.findElement(By.cssSelector(".continue > span")).click();
        
        WebElement product2 = driver.findElement(By.cssSelector("#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.last-line.first-item-of-tablet-line.first-item-of-mobile-line.last-mobile-line > div > div.left-block > div > a.product_img_link > img"));
        action.moveToElement(product2).perform();
        driver.findElement(By.cssSelector("#homefeatured > .ajax_block_product:nth-child(7) .button:nth-child(1) > span")).click();
        driver.findElement(By.cssSelector(".continue > span")).click();
    }

    @When("^the user clicks on the \"([^\"]*)\" button$")
    public void the_user_clicks_on_the_something_button(String strArg1) throws Throwable {
    	System.out.println("User clicks on shopping cart");
    	    	
    	driver.findElement(By.cssSelector("b")).click();
    }

    @Then("^an iFrame opens up$")
    public void an_iframe_opens_up() throws Throwable {
        System.out.println("iFrame");
    }

    @Then("^the user is redirected to the \"([^\"]*)\" page$")
    public void the_user_is_redirected_to_the_something_page(String strArg1) throws Throwable {
    	assertThat(driver.getTitle(), is("Order - My Store"));
    	
    	String val1 = driver.findElement(By.cssSelector("#product_1_1_0_0 > td.cart_quantity.text-center > input.cart_quantity_input.form-control.grey")).getAttribute("value");
    	assertEquals(val1, "1");
    	
    	String val2 = driver.findElement(By.cssSelector("#product_7_34_0_0 > td.cart_quantity.text-center > input.cart_quantity_input.form-control.grey")).getAttribute("value");
    	assertEquals(val2, "1");
    	
    	driver.close();
    }

}
