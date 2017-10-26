package com.test.demostore.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFox extends BaseDriver {

	public void init() {		// TODO Auto-generated method stub

		System.setProperty("webdriver.gecko.driver", SeleniumConfig.userProjDir+"\\webdrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
	}	
}
