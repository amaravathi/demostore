package com.test.demostore.stepdefinition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.demostore.helper.BaseDriver;
import com.test.demostore.helper.Chrome;
import com.test.demostore.helper.FireFox;
import com.test.demostore.helper.SeleniumConfig;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static BaseDriver driver;
	private static SeleniumConfig seleniumConfig;	
	
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     * @throws IOException 
     */
	
	
	@Before
    public void setup() throws IOException {
		
    	System.out.println("Called setup()");    	
    	DOMConfigurator.configure("log4j.xml");
  	
    	String browserName = "Chrome"; // this shoud get read from config
     	driver = acquireDriver(browserName);
    	if (driver != null)
    		System.out.println("Driver Initialized successfully");
    	
    }

	@After
	public void tearDown() {
		driver.deinit();
	}
	
	public BaseDriver acquireDriver(String browserName) {
		if(browserName.compareToIgnoreCase("Chrome") == 0)
			return  new Chrome();
		else if (browserName.compareToIgnoreCase("Firefox")==0)
			return new FireFox();
		else
			return null;
	}
}
