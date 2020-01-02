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
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class MenuOptionsStepDef {
	private WebDriver driver;
	private Map<String, Object> vars;
	private JavascriptExecutor js;

    @Given("^the user navigates to the store home page$")
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

    @When("^the user hovers over the \"([^\"]*)\" header$")
    public void the_user_hovers_over_the_something_header(String strArg1) throws Throwable {
    	Actions action = new Actions(driver);
    	
    	WebElement category = driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(1) > a"));
    	
    	action.moveToElement(category).perform();
    }

    @When("^the user clicks on \"([^\"]*)\"$")
    public void the_user_clicks_on_something(String strArg1) throws Throwable {
    	driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(1) > ul > li:nth-child(2) > ul > li:nth-child(2) > a")).click();
    }

    @When("^the user adds the \"([^\"]*)\" to the cart$")
    public void the_user_adds_the_something_to_the_cart(String strArg1) throws Throwable {
    	Actions action = new Actions(driver);
    	
    	WebElement dress = driver.findElement(By.cssSelector("#center_column > ul > li > div > div.left-block > div > a.product_img_link > img"));
    	
    	action.moveToElement(dress).perform();
    	driver.findElement(By.cssSelector("#center_column > ul > li > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")).click();
    }

    @When("^the user clicks on the cart button$")
    public void the_user_clicks_on_the_cart_button() throws Throwable {
    	System.out.println("User clicks on shopping cart");
    	
    	driver.findElement(By.cssSelector("b")).click();
    }

    @Then("^a submenu is displayed$")
    public void a_submenu_is_displayed() throws Throwable {
    	String actual = driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(1) > ul > li:nth-child(2) > a")).getText();
    	String expected = "DRESSES";
    	assertEquals(actual, expected);
    }

    @Then("^the page will redirect to the \"([^\"]*)\" page$")
    public void the_page_will_redirect_to_the_something_page(String strArg1) throws Throwable {
    	String actual = driver.findElement(By.cssSelector("#center_column > h1 > span.cat-name")).getText().trim().toUpperCase();
    	String expected = "EVENING DRESSES";
    	
    	assertEquals(actual, expected);
    }

    @Then("^an iFrame opens$")
    public void an_iframe_opens() throws Throwable {
        String actual = driver.findElement(By.cssSelector("#layer_cart")).getAttribute("id");
        String expected = "layer_cart";
        
        assertEquals(expected, actual);
    }

    @Then("^the page will redirect to the cart page$")
    public void the_page_will_redirect_to_the_cart_page() throws Throwable {
    	assertThat(driver.getTitle(), is("Order - My Store"));
    	
    	String value = driver.findElement(By.cssSelector("#product_4_16_0_0 > td.cart_quantity.text-center > input.cart_quantity_input.form-control.grey")).getAttribute("value");
    	
    	assertEquals("1", value);
    	driver.close();
    }

    @And("^the user can click continue$")
    public void the_user_can_click_continue() throws Throwable {
    	driver.findElement(By.cssSelector(".continue > span")).click();
    }

}
