package com.test.demostore.stepdefinition;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.WebDriver;

import com.test.demostore.helper.BaseDriver;
import com.test.demostore.helper.Log;
import com.test.demostore.pageobjects.HomePageObject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePage extends BasicStep {
	
	
	private HomePageObject homePageObject; 
	
	public HomePage() {
		super();
	}
	@When("^I open demo store website$")
	public void i_open_demo_store_website() throws Throwable {	    
		homePageObject = new HomePageObject(baseDriver.getDriver());
		homePageObject.navigateTo("http://store.demoqa.com/");	
				
		System.out.println("Simple Name : "+ this.getClass().getSimpleName());
		homePageObject.captureScreenShot("homePage");
	}
	@Then("^I validate title$")
	public void i_validate_title() throws Throwable {
		assertEquals("ONLINE STORE | Toolsqa Dummy Test site",homePageObject.getTitle());
	    assertEquals("http://store.demoqa.com/",homePageObject.getCurrentUrl());	
	    Log.info("Loaded Home Page");
	}

}
