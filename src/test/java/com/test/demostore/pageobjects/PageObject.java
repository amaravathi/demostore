package com.test.demostore.pageobjects;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.demostore.helper.Log;
import com.test.demostore.helper.SeleniumConfig;

public  abstract class PageObject {

	public static WebDriver driver;	

	public  PageObject(WebDriver driver){
		PageObject.driver = driver;		
		
	}
	public void navigateTo(String URL) {
		driver.get(URL);
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(SeleniumConfig.implicitWaitTime, TimeUnit.SECONDS);
	}
	public void Click(WebElement element) {
		element.click();
	}	
	public void Submit(By locator) {		
		find(locator).submit();		
	}
	public WebElement find(By locator) {
		return driver.findElement(locator);
	}
	public void typeText(WebElement element, String textData) {
		element.sendKeys(textData);
	}
	public boolean isDisplayed(By locator) {
		try {
			return find(locator).isDisplayed();
			
		}catch(NoSuchElementException ex) {
			return false;
		}		
	}
	public String getTitle() {
		return driver.getTitle();
	}
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	public void switchTo() {
		driver.switchTo();
	}
	public void captureScreenShot(String Name) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
		String fileName =SeleniumConfig.imgDir+Name+".jpg";
           try {
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.error(e.getLocalizedMessage());
		}
	}
	public void waitForEmtVisisbility(By emtLocator) {
		WebDriverWait wait = new WebDriverWait(driver, SeleniumConfig.explicitWaitTime);
		wait.until( ExpectedConditions.visibilityOfElementLocated(emtLocator) );		
	}
	
}
