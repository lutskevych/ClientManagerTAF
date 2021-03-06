package stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.User;
import data.World;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import spec.ReqSpecification;

import java.time.LocalDate;
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
        if (!(world.user.getUid().isEmpty())) {
            given().spec(ReqSpecification.reqSpec)
                    .delete(world.user.getUid());
        }
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
                .body(getBody(world.user.getUid(), world.user.getFirstName(), changedLastName,
                        world.user.getGender(), world.user.getAge(), world.user.getEmail()))
                .put();
    }

    @When("^administrator inserts user with \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([a-zA-Z]+)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void administratorInsertsUserWithAllRequestedFields(String uid, String firstName, String lastName,
                                             String gender, int age, String email) {
        world.user = User.newBuilder()
                .setUid(uid)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setAge(age)
                .setEmail(email)
                .build();

        response = given()
                .spec(ReqSpecification.reqSpec)
                .body(getBody(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(),
                        world.user.getGender(), world.user.getAge(), world.user.getEmail()))
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
                .body(getBody(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(), incorrectGender,
                        world.user.getAge(), world.user.getEmail()))
                .put();
    }

    @When("^administrator updates age field with \"([^\"]*)\"$")
    public void administratorUpdatesAgeFieldWith(int incorrectAge) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .body(getBody(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(), world.user.getGender(),
                        incorrectAge, world.user.getEmail()))
                .put();
    }

    @When("^administrator inserts user with \"([^\"]*)\" \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void administratorInsertsUserWithFullName(String incorrectFullName, String uid, String firstName, String lastName,
                                             String gender, int age, String email) {
        world.user = User.newBuilder()
                .setUid(uid)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setAge(age)
                .setEmail(email)
                .setFullName(incorrectFullName)
                .build();
        response = given()
                .spec(ReqSpecification.reqSpec)
                .body(getBodyWithFullName(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(),
                        world.user.getGender(), world.user.getAge(), world.user.getEmail(), world.user.getFullName()))
                .post();
    }

    @When("^administrator inserts user with \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\"$")
    public void administratorInsertsUserWithDateOfBirth(String uid, String firstName, String lastName,
                                             String gender, int age, String email,int incorrectDateOfBirth) {
        world.user = User.newBuilder()
                .setUid(uid)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setAge(age)
                .setEmail(email)
                .setDateOfBirth(incorrectDateOfBirth)
                .build();
        response = given()
                .spec(ReqSpecification.reqSpec)
                .body(getBodyWithDateOfBirth(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(),
                        world.user.getGender(), world.user.getAge(), world.user.getEmail(), world.user.getDateOfBirth()))
                .post();
    }

    @Then("^changes are successful$")
    public void changesAreSuccsessful() {
        response.then()
                .statusCode(204);
    }

    @Then("^response have only gender users$")
    public void responseHaveOnlyUsers() {
        response.then()
                .body("gender", hasItem(world.user.getGender()));
    }

    @Then("^administrator deletes user$")
    public void administratorDeletesUserWith() {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .delete(world.user.getUid());
    }

    @Then("^response have only user with correct uid$")
    public void responseHaveOnlyUserWithCorrectUid() {
        response.then()
                .body("userUid", equalTo(world.user.getUid()));
    }

    @Then("^error message with \"([^\"]*)\" appears$")
    public void errorMessageWithAppears(int statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    @Then("^user appeared with correct full name$")
    public void userAppearedWithCorrectFullName() {
        getResponse(world.user.getUid());
        response.then()
                .body("fullName", equalTo(world.user.getFirstName() + " " + world.user.getLastName()));
    }

    @Then("^administrator updates full name field with \"([^\"]*)\"$")
    public void administratorUpdatesFullNameFieldWith(String incorrectFullName) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .body(getBodyWithFullName(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(),
                        world.user.getGender(), world.user.getAge(), world.user.getEmail(), incorrectFullName))
                .put();
    }

    @Then("^administrator updates date of birth field with \"([^\"]*)\"$")
    public void administratorUpdatesDateOfBirthFieldWith(int incorrectDateOfBirth) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .body(getBodyWithDateOfBirth(world.user.getUid(), world.user.getFirstName(), world.user.getLastName(),
                        world.user.getGender(), world.user.getAge(), world.user.getEmail(), incorrectDateOfBirth))
                .put();
    }


    @Then("^user appeared with correct date of birth$")
    public void userAppearedWithCorrectDateOfBirth() {
        getResponse(world.user.getUid());
        response.then()
                .body("dateOfBirth", equalTo(LocalDate.now().minusYears(world.user.getAge()).getYear()));
    }

    @And("^record has correct data$")
    public void recordWithHasCorrectData() {
        getResponse(world.user.getUid());
        response.then()
                .body("userUid", equalTo(world.user.getUid()))
                .body("firstName", equalTo(world.user.getFirstName()))
                .body("lastName", equalTo(world.user.getLastName()))
                .body("age", equalTo(world.user.getAge()))
                .body("email", equalTo(world.user.getEmail()))
                .body("fullName", equalTo(world.user.getFirstName() + " " + world.user.getLastName()))
                .body("dateOfBirth", equalTo(LocalDate.now().minusYears(world.user.getAge()).getYear()));
    }

    @And("^field with \"([^\"]*)\" has correct data$")
    public void fieldWithForUserWithHasCorrectData(String changedLastName) {
        getResponse(world.user.getUid());
        response.then()
                .body("lastName", equalTo(changedLastName));
    }

    @And("^user does not exist$")
    public void userDoesNotExist() {
        getResponse(world.user.getUid());
        response.then()
                .statusCode(404);
    }

    @And("^user appeared$")
    public void userAppeared() {
        getResponse(world.user.getUid());
        response.then()
                .statusCode(200);
    }

    private void getResponse(String uid) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .get(uid);
    }

    private String getBody(String uid, String firstName, String lastName,
                           String gender, int age, String email) {
        return "{" +
                "\"userUid\":\"" + uid + "\",\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"age\":" + age + ",\n" +
                "\"email\":\"" + email + "\"\n" +
                "}";
    }

    private String getBodyWithFullName(String uid, String firstName, String lastName,
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

    private String getBodyWithDateOfBirth(String uid, String firstName, String lastName,
                                       String gender, int age, String email, int dateOfBirth) {
        return "{" +
                "\"userUid\":\"" + uid + "\",\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"age\":" + age + ",\n" +
                "\"email\":\"" + email + "\",\n" +
                "\"dateOfBirth\":\"" + dateOfBirth + "\"\n" +
                "}";
    }
}
