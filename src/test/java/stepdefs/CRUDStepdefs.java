package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CRUDStepdefs {

    private Response response;

    private World world;

    public CRUDStepdefs(World world) {
        this.world = world;
    }

    @After("@delete")
    public void afterCRUScenario(){
        given().spec(ReqSpecification.reqSpec)
                .basePath(world.user.getUid())
                .delete();
    }

    @When("^administrator select gender users$")
    public void administratorSelectUsers() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .queryParam("gender", world.user.getGender())
                .get();
    }

    @When("^administrator change last name on \"([^\"]*)\"$")
    public void administratorChangeLastNameOn(String changedLastName) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .with()
                .body(getBody(world.user.getUid(), world.user.getFirstName(), changedLastName,
                        world.user.getGender(), world.user.getAge(), world.user.getEmail(), world.user.getFullName()))
                .when()
                .put();
    }

    @When("^administrator inserts user with \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([a-zA-Z]+)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void administratorInsertsUserWith(String uid, String firstName, String lastName,
                                             String gender, int age, String email, String fullName) {
        world.user = new User(uid, firstName, lastName, gender, age, email, fullName);
        response = given()
                .spec(ReqSpecification.reqSpec)
                .with()
                .body(getBody(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(),
                        world.user.getGender(), world.user.getAge(), world.user.getEmail(), world.user.getFullName()))
                .when()
                .post();
    }

    @When("^administrator select user by uid$")
    public void administratorSelectUserByUid() {
        getResponse(world.user.getUid());
    }

    @When("^administrator updates gender field with \"([^\"]*)\"$")
    public void administratorUpdatesGenderFieldWith(String incorrectGender) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .with()
                .body(getBody(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(), incorrectGender,
                        world.user.getAge(), world.user.getEmail(), world.user.getFullName()))
                .when()
                .put();
    }

    @When("^administrator updates age field with \"([^\"]*)\"$")
    public void administratorUpdatesAgeFieldWith(int incorrectAge) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .with()
                .body(getBody(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(), world.user.getGender(),
                        incorrectAge, world.user.getEmail(), world.user.getFullName()))
                .when()
                .put();
    }

    @Then("^user was updated$")
    public void userWasUpdated() {
        response.then().assertThat().statusCode(204);
    }

    @Then("^user was deleted$")
    public void userWasDeleted() {
        response.then().assertThat().statusCode(204);
    }

    @Then("^response have only gender users$")
    public void responseHaveOnlyUsers() {
        response.then()
                .assertThat()
                .body("gender", hasItem(world.user.getGender()));
    }

    @Then("^administrator deletes user$")
    public void administratorDeletesUserWith() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .basePath(world.user.getUid())
                .delete();
    }

    @Then("^user was inserted$")
    public void userWithWasInserted() {
        response.then().assertThat().statusCode(204);
        getResponse(world.user.getUid());
        response.then().assertThat().statusCode(200);
    }

    @Then("^response have only user with correct uid$")
    public void responseHaveOnlyUserWithCorrectUid() {
        response.then()
                .assertThat()
                .body("userUid", equalTo(world.user.getUid()));
    }

    @Then("^error message with \"([^\"]*)\" appears$")
    public void errorMessageWithAppears(int statusCode) {
        response.then()
                .assertThat()
                .statusCode(statusCode);
    }

    @And("^record has correct data$")
    public void recordWithHasCorrectData() {
        getResponse(world.user.getUid());
        response.then()
                .assertThat()
                .body("userUid", equalTo(world.user.getUid()))
                .body("firstName", equalTo(world.user.getFirstName()))
                .body("lastName", equalTo(world.user.getLastName()))
                .body("age", equalTo(world.user.getAge()))
                .body("email", equalTo(world.user.getEmail()))
                .body("fullName", (equalTo(world.user.getFullName())));
    }

    @And("^field with \"([^\"]*)\" has correct data$")
    public void fieldWithForUserWithHasCorrectData(String changedLastName) {
        getResponse(world.user.getUid());
        response.then()
                .assertThat()
                .body("lastName", equalTo(changedLastName));
    }

    @And("^user does not exist$")
    public void userDoesNotExist() {
        getResponse(world.user.getUid());
        response.then().assertThat().statusCode(404);
    }

    private void getResponse(String uid) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .basePath(uid)
                .get();
    }

    private String getBody(String uid, String firstName, String lastName,
                           String gender, int age, String email, String fullName) {
        return "{" +
                "\"userUid\":\"" + uid + "\",\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"age\":" + age + ",\n" +
                "\"email\":\"" + email + "\",\n" +
                "\"fullName\":\"" + fullName + "\"\n" +
                "}";
    }

}
