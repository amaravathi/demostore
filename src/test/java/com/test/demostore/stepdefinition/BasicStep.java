package com.test.demostore.stepdefinition;

import com.test.demostore.helper.BaseDriver;

public  abstract class BasicStep {
	public BaseDriver baseDriver;
	BasicStep(){
		baseDriver = Hooks.driver;
	}

}
