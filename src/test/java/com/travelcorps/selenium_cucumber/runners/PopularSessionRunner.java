package com.travelcorps.selenium_cucumber.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/com/perscholas/selenium_cucumber_template/features/popularSessionRequirement.feature",
		glue="com/perscholas/selenium_cucumber_template/step_definitions")
public class PopularSessionRunner {

}
