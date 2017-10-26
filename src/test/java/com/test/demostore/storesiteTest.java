package com.test.demostore;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:Features"
		,glue={"com.test.demostore.stepdefinition"}
		)

public class storesiteTest {

}
