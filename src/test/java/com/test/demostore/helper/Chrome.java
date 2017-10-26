package com.test.demostore.helper;

import org.openqa.selenium.chrome.ChromeDriver;

	
public class Chrome extends BaseDriver {

	public Chrome() {
		System.out.println("RootDir "+ SeleniumConfig.userProjDir);
		System.out.println("RootDir "+ SeleniumConfig.errImgDir);
		
		System.setProperty("webdriver.chrome.driver", SeleniumConfig.userProjDir+"\\webdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public void deinit() {
		// TODO Auto-generated method stub
		driver.quit();
	}
}
