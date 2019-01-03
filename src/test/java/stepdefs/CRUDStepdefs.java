package stepdefs;

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
        response.getBody().print();
    }

    @When("^administrator change last name on \"([^\"]*)\"$")
    public void administratorChangeLastNameOn(String changedLastName) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .with()
                .body("{" +
                        "\"userUid\":\"" + world.user.getUid() + "\",\n" +
                        "\"firstName\":\"" + world.user.getFirstName() + "\",\n" +
                        "\"lastName\":\"" + changedLastName + "\",\n" +
                        "\"gender\":\"" + world.user.getGender() + "\",\n" +
                        "\"age\":" + world.user.getAge() + ",\n" +
                        "\"email\":\"" + world.user.getEmail() + "\",\n" +
                        "\"dateOfBirth\":" + world.user.getDateOfBirth() + ",\n" +
                        "\"fullName\":\"" + world.user.getFullName() + "\"\n" +
                        "}")
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
                .body("{" +
                        "\"userUid\":\"" + uid + "\",\n" +
                        "\"firstName\":\"" + firstName + "\",\n" +
                        "\"lastName\":\"" + lastName + "\",\n" +
                        "\"gender\":\"" + gender + "\",\n" +
                        "\"age\":" + age + 1 + ",\n" + //FIXME
                        "\"email\":\"" + email + "\",\n" +
                        "\"fullName\":\"" + fullName + "\"\n" +
                        "}")
                .when()
                .post();
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

    @And("^user was disappeared$")
    public void userWasDisappeared() {
        getResponse(world.user.getUid());
        response.then().assertThat().statusCode(404);
    }

    private void getResponse(String uid) {
        response = given()
                .spec(ReqSpecification.reqSpec)
                .basePath(uid)
                .get();
    }

}
