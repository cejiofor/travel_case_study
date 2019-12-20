package com.perscholas.selenium_cucumber_template.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/com/perscholas/selenium_cucumber_template/features/menuOptionsRequirement.feature",
		glue="com/perscholas/selenium_cucumber_template/step_definitions")

public class MenuOptionsRunner {

}
