package Runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="FeatureFiles" 
		,glue= {"StepDefinitionFiles"} 
		,tags="@RestAssuredCucumber" 
		,plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)
public class RestAssuredJUnitTestRunner {

}
