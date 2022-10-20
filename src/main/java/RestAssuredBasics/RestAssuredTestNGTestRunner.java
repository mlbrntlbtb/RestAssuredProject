package RestAssuredBasics;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="FeatureFiles"
		,glue="StepDefinitionFiles"
		,plugin= {"pretty"}
		,tags="@RestAssuredCucumber"
		
		)
public class RestAssuredTestNGTestRunner extends AbstractTestNGCucumberTests
{

}
