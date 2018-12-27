import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "src/test/java/com.shkulova.learningrestasured.stepdefs",
        features = "src/test/resource/com.shkulova.learningrestasured.feature",
        dryRun = true
)
public class RunTests {

}
