package com.test.demostore.stepdefinition;

import static org.testng.AssertJUnit.assertEquals;

import com.test.demostore.helper.DemoUtils;
import com.test.demostore.helper.Log;
import com.test.demostore.helper.SeleniumConfig;
import com.test.demostore.pageobjects.UserRegisterPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class UserRegister  extends BasicStep{
	private UserRegisterPage  userRegisterPage;
	
	public UserRegister() {
		super();
	}
	@Given("^We are on userRegistration page$")
	public void we_are_on_userRegistration_page() throws Throwable {
		userRegisterPage = new UserRegisterPage(baseDriver.getDriver());
		userRegisterPage.navigateTo(SeleniumConfig.userRegisterPageUrl);
	}

	@Then("^UserName and Email textboxes should be available$")
	public void username_and_Email_textboxes_should_be_available() throws Throwable {
	  Log.info("Current Url" + userRegisterPage.getCurrentUrl());
	  
	  if ( userRegisterPage.userName.isDisplayed() &&
			  userRegisterPage.userEmail.isDisplayed() )
		  Log.info("Register Page Loaded Successfully");
	  else
		  Log.info("There s something wrong with regisstration page");
	}
	@Then("^Enter details in textboxes$")
	public void enter_details_in_textboxes() throws Throwable {	    
	    userRegisterPage.typeText(userRegisterPage.userName, SeleniumConfig.newUserName);
	    userRegisterPage.typeText(userRegisterPage.userEmail, SeleniumConfig.userRegistrationEmail);
	}

	@Then("^Submit$")
	public void registrationSubmit() throws Throwable {
		
		String imgFileName= DemoUtils.getImageFileName();
		String className = this.getClass().getSimpleName();
		//String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
	    userRegisterPage.registerSubmit.submit();	
	    Log.info("Moved To URL : "+ userRegisterPage.getCurrentUrl());
	  //  userRegisterPage.captureScreenShot(imgFileName);
	   assertEquals("Registration complete. Please check your email.",userRegisterPage.registrationMsg.getText());
		}catch(Exception ex) {
		//	userRegisterPage.captureScreenShot(imgFileName);
		}
	}


}
