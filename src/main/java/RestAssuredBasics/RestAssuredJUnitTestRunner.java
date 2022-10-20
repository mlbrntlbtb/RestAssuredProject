package RestAssuredBasics;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="FeatureFiles"
		,glue="StepDefinitionFiles"
		,plugin= {"pretty"}
		,tags="@RestAssuredCucumber"
		)
public class RestAssuredJUnitTestRunner 
{

}
