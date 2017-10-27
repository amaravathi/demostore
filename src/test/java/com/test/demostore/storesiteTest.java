package com.test.demostore;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:Features",
		glue={"com.test.demostore.stepdefinition"},
		tags= {"@SmokeTest"},
		plugin = { 
				"html:target/cucumber-html-reports",
		        "json:target/cucumber-reports/cucumber.json",
		        "pretty:target/cucumber-reports/cucumber-pretty.txt",
		        "usage:target/cucumber-reports/cucumber-usage.json",
		        "junit:target/cucumber-reports/cucumber-results.xml" } 
		)

public class storesiteTest {

}
