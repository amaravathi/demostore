package com.test.demostore.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageObject extends PageObject {
	

	public HomePageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub		
	}
	
	@FindBy(how=How.XPATH, using=".//*[@id='account']/a")
	public static WebElement myAccount;	
	
}
