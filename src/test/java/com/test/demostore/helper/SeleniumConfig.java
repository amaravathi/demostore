package com.test.demostore.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumConfig {
	public static String userProjDir;
	public static String imgDir;
	
	public static String gmailUserId;
	public static String gmailUserPwd;
	public static String demoStroreUserId;
	public static String demoStroreUserPwd;
	public static int explicitWaitTime;
	public static int implicitWaitTime;
	public static String demoStoreHomePage;
	public static String demoStoreLoginPage;	
	public static String useRegisterEmailSubject;
	public static String userEmailClient; 
	public static String userEmailStore;
	public static String userInboxName;
	public static String userSpamName;
	public static String userRegisterPageUrl;
	public static String newUserName;
	public static String userRegistrationEmail;
	public static String browserName;
	
	
	static {
		Properties userProps = System.getProperties();
		
		Properties configProp = new Properties();
		InputStream iStream = null;
		try {
			iStream = SeleniumConfig.class.getClassLoader().getResourceAsStream("config.properties");			
			configProp.load(iStream);
			} catch (IOException e) {
				Log.error(e.getLocalizedMessage());
		}
		finally{
        	if(iStream!=null){
        		try {
        		iStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
		if ( configProp.getProperty("user.dir") == null  || configProp.getProperty("user.dir").isEmpty())
			configProp.setProperty("user.dir", userProps.getProperty("user.dir") );	
		
			load(configProp);
		}
	
	public static void load(Properties props) {
		userProjDir = props.getProperty("user.dir")+"\\";
		imgDir = props.getProperty("user.dir")+"\\images\\";		

		gmailUserId= props.getProperty("user.gmail.id");
		gmailUserPwd = props.getProperty("user.gmail.pwd");
		demoStroreUserId= props.getProperty("demo.store.user.id");
		demoStroreUserPwd = props.getProperty("demo.store.user.pwd");
		explicitWaitTime = Integer.parseInt(props.getProperty("demo.store.explicitwait.time"));
		implicitWaitTime = Integer.parseInt(props.getProperty("demo.store.implicitwait.time"));				
		demoStoreHomePage = props.getProperty("demo.store.homepage.url");
		demoStoreLoginPage = props.getProperty("demo.store.loginpage.url");
		useRegisterEmailSubject = props.getProperty("demo.store.resgister.email.subject");		
		userEmailClient = props.getProperty("user.email.client");
		userEmailStore  = props.getProperty("user.email.store");		
		userInboxName = props.getProperty("user.email.inbox.name");
		userSpamName = props.getProperty("user.email.spam.name");
		userRegisterPageUrl = props.getProperty("demo.store.user.register.url");
		newUserName = props.getProperty("demo.store.registratiom.newuser");
		userRegistrationEmail = props.getProperty("demo.store.registationemail");
		browserName = props.getProperty("test.browserName");
		
	}
}
