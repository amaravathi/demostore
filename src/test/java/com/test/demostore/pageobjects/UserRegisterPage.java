package com.test.demostore.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UserRegisterPage  extends PageObject{

	public UserRegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(how=How.XPATH, using=".//*[@id='account']/a")
	public static WebElement myAccount;	
	
	@FindBy(how=How.LINK_TEXT, using="Register")
	public static WebElement registerLink;
	
	@FindBy(how=How.ID, using="user_login")
	public static WebElement userName;
	
	@FindBy(how=How.ID, using="user_email")
	public static WebElement userEmail;	
	

	@FindBy(how=How.XPATH, using=".//input[@name='user_email']")
	public static WebElement registerSubmit;	
	
	@FindBy(how=How.CLASS_NAME, using="message")
	public static WebElement registrationMsg; 
}
