package stepdefs;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONSchemaValidateStepdefs {

    private Response response;

    @When("^user navigates to endpoint$")
    public void userNavigatesToEndpoint() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .get();
    }

    @Then("^response has correct schema$")
    public void responseHasCorrectSchema() {
        JsonSchemaFactory jsonSchemaFactory = buildJSONSchema();
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema.json").using(jsonSchemaFactory));
    }

    private JsonSchemaFactory buildJSONSchema() {
        return JsonSchemaFactory.newBuilder()
                                .setValidationConfiguration(
                                        ValidationConfiguration.newBuilder()
                                                                .setDefaultVersion(SchemaVersion.DRAFTV4)
                                                                .freeze()
                                )
                                .freeze();
    }
}
