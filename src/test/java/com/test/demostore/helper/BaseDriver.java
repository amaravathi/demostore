package com.test.demostore.helper;

import org.openqa.selenium.WebDriver;

public abstract class  BaseDriver {
	public WebDriver driver;
	
	public void init() {
		// TODO Auto-generated method stub, shoud read from config file		
		driver.manage().deleteAllCookies();
    	driver.manage().window().maximize();
	}
	
	public void deinit() {
		// TODO Auto-generated method stub
		driver.quit();
	}
	public WebDriver getDriver() {
		return driver;
	}
}
