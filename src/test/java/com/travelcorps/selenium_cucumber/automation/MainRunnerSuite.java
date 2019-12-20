package com.perscholas.selenium_cucumber_template.automation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.perscholas.selenium_cucumber_template.runners.MenuOptionsRunner;
import com.perscholas.selenium_cucumber_template.runners.PopularSessionRunner;

@RunWith(Suite.class)
@SuiteClasses({MenuOptionsRunner.class, PopularSessionRunner.class})
public class MainRunnerSuite {

}
