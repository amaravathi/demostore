package com.test.demostore.stepdefinition;

import static org.testng.AssertJUnit.assertEquals;

import com.test.demostore.helper.Log;
import com.test.demostore.pageobjects.UserRegisterPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserRegister  extends BasicStep{
	private UserRegisterPage  userRegisterPage;
	
	public UserRegister() {
		super();
	}
	@Given("^We are UserRegister Page$")
	public void we_are_UserRegister_Page() throws Throwable {
		userRegisterPage = new UserRegisterPage(baseDriver.getDriver());
		userRegisterPage.navigateTo("http://store.demoqa.com/products-page/your-account/");
	}

	@When("^Click on Register link$")
	public void click_on_Register_link() throws Throwable {
		userRegisterPage.registerLink.click();	  
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
	    userRegisterPage.typeText(userRegisterPage.userName, "userName4");
	    userRegisterPage.typeText(userRegisterPage.userEmail, "store.demo.test@gmail.com");
	}

	@Then("^Submit$")
	public void submit() throws Throwable {
		String errFileName = this.getClass().getEnclosingMethod().getName();
		try {
	    userRegisterPage.registerSubmit.submit();	
	    Log.info("Moved To URL : "+ userRegisterPage.getCurrentUrl());
	    userRegisterPage.captureScreenShot(errFileName);
	   assertEquals("Registration complete. Please check your email.",userRegisterPage.registrationMsg.getText());
		}catch(Exception ex) {
			userRegisterPage.captureScreenShot(errFileName);
		}
	}


}
