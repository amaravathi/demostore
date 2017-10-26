package com.test.demostore.actions;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.test.demostore.pageobjects.HomePageObject;


public class MyAccount {
	
public static void Execute(WebDriver driver) throws Exception{
		
		HomePageObject.myAccount.click();
		Reporter.log("Found, myAccount and successfully clicked");
	}
}
