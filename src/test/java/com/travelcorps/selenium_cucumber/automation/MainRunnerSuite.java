package com.travelcorps.selenium_cucumber.automation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.travelcorps.selenium_cucumber.runners.MenuOptionsRunner;
import com.travelcorps.selenium_cucumber.runners.PopularSessionRunner;

@RunWith(Suite.class)
@SuiteClasses({MenuOptionsRunner.class, PopularSessionRunner.class})
public class MainRunnerSuite {

}
