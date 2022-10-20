package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="FeatureFiles"
		,glue="StepDefinitionFiles"
		,plugin= {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		,tags="@RestAssuredCucumber"
		)
public class RestAssuredTestNGTestRunner extends AbstractTestNGCucumberTests
{

}
