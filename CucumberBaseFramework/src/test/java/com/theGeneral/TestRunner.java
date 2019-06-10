package com.theGeneral;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.theGeneral.utilities.BaseClass;
import com.theGeneral.utilities.Report;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//{"classpath:multicolumn.feature","classpath:outline.feature"},



@CucumberOptions(features = "src/test/resources/features",monochrome=false,strict=true, glue = { "com.theGeneral.stepDefinations" }, tags = {
				"@Login" }, plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty",
						"json:target/cucumber-reports/CucumberTestReport.json",
						"rerun:target/cucumber-reports/rerun.txt" })
public class TestRunner extends BaseClass {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());	
				
	      }
	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
		// the 'featureWrapper' parameter solely exists to display the feature file in a
		// test report
		String sampleString = System.getProperty("TestCaseId");
	      String[] items = sampleString.split(",");
	      List<String> itemList = Arrays.asList(items);
	      System.out.println(itemList);	     
	      for (int i = 0; i < itemList.size(); i++) {
	    	    System.out.println(itemList.get(i));	    
	          readSpecificTestData(itemList.get(i));
	          initialization();
				initiateReport(itemList.get(i));
		testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
		Report.writeContents(report);	
		if (dr == null) {
			 return;
			 }
		 dr.quit();
			 dr = null;

			if (testNGCucumberRunner == null) {
				return;
			}
			testNGCucumberRunner.finish();
	}
	}

	/**
	 * Returns two dimensional array of PickleEventWrapper scenarios with their
	 * associated CucumberFeatureWrapper feature.
	 *
	 * @return a two dimensional array of scenarios features.
	 */
	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}
	


	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		 if (dr == null) {
		 return;
		 }
	 dr.quit();
		 dr = null;

		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();

	}

	
}



