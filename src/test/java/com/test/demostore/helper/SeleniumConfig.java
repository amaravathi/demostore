package com.test.demostore.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumConfig {
	public static String userProjDir;
	public static String errImgDir;
	
	static {
		Properties userProps = System.getProperties();
		
		//Read Data from config file
		Properties configProp = new Properties();
		InputStream iStream = null;
		try {
			iStream = new FileInputStream("config.properties");
			configProp.load(iStream);
			} catch (IOException e) {
			// TODO Auto-generated catch block
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
		if ( configProp.getProperty("user.dir") == null )
			configProp.setProperty("user.dir", userProps.getProperty("user.dir") );	
		
			load(configProp);
		}
	
	public static void load(Properties props) {
		userProjDir = props.getProperty("user.dir")+"\\";
		errImgDir = props.getProperty("user.dir")+"\\images\\";	
	}
}
