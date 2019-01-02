package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CRUDStepdefs {

    private Response response;

    @When("^administrator inserts user$")
    public void administratorInsertsUser() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .with()
                .body("{\n" +
                        "        \"userUid\":\"d22b8953-c87e-4729-afb8-fbce9d1dc9e4\" ,\n" +
                        "            \"firstName\": \"Anna\",\n" +
                        "            \"lastName\": \"Jones\",\n" +
                        "            \"gender\": \"FEMALE\",\n" +
                        "            \"age\": 21,\n" +
                        "            \"email\": \"joe.jones@gmail.com\",\n" +
                        "            \"dateOfBirth\": 1997,\n" +
                        "            \"fullName\": \"Anna Jones\"\n" +
                        "    }")
                .when()
                .post();
    }

    @When("^administrator deletes user$")
    public void administratorDeletesUser() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .basePath("d22b8953-c87e-4729-afb8-fbce9d1dc9e4")
                .delete();
    }

    @When("^administrator updates user$")
    public void administratorUpdatesUser() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .body("{\n" +
                        "        \"userUid\":\"d22b8953-c87e-4729-afb8-fbce9d1dc9e4\" ,\n" +
                        "            \"firstName\": \"Anna\",\n" +
                        "            \"lastName\": \"Jones\",\n" +
                        "            \"gender\": \"FEMALE\",\n" +
                        "            \"age\": 23,\n" +
                        "            \"email\": \"joe.jones@gmail.com\",\n" +
                        "            \"dateOfBirth\": 1997,\n" +
                        "            \"fullName\": \"Anna Jones\"\n" +
                        "    }")
                .put();
    }

    @When("^administrator select \"([a-zA-Z]+)\" users$")
    public void administratorSelectUsers(String gender) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .queryParam("gender", gender)
                .get();
    }

    @Then("^user was inserted$")
    public void userWasInserted() {
        response.then().assertThat().statusCode(204);
    }

    @Then("^user was updated$")
    public void userWasUpdated() {
        response.then().assertThat().statusCode(204);
    }

    @Then("^user was deleted$")
    public void userWasDeleted() {
        response.then().assertThat().statusCode(204);
    }

    @Then("^response have only \"([a-zA-z]+)\" users$")
    public void responseHaveOnlyUsers(String gender) {
        response.then()
                .assertThat()
                .body("gender", contains(gender));
    }

    @And("^record has correct data$")
    public void recordHasCorrectData() {
        getResponse();
        response.then()
                .assertThat()
                .body("userUid", equalTo("d22b8953-c87e-4729-afb8-fbce9d1dc9e4"))
                .body("firstName", equalTo("Anna"))
                .body("lastName", equalTo("Jones"))
                .body("gender", equalTo("FEMALE"))
                .body("age", equalTo(21))
                .body("email", equalTo("joe.jones@gmail.com"))
                .body("dateOfBirth", equalTo(1998))
                .body("fullName", (equalTo("Anna Jones")));
    }

    @And("^updated fields has correct data$")
    public void updatedFieldsHasCorrectData() {
        getResponse();
        response.then()
                .assertThat()
                .body("age", equalTo(23));
    }

    @And("^correct record disappeared$")
    public void correctRecordDisappeared() {
        getResponse();
        response.then().assertThat().statusCode(404);
    }

    private void getResponse() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .basePath("d22b8953-c87e-4729-afb8-fbce9d1dc9e4")
                .get();
    }

}
