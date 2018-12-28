package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import stepdefs.ReqSpecification;

import static io.restassured.RestAssured.given;

public class HooksForCRUD {
    @Before("@read/update, @delete")
    public void beforeRUDScenario() {
        given().spec(ReqSpecification.reqSpec)
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

    @After("@read/update, @insert")
    public void afterCRUScenario(){
        given().spec(ReqSpecification.reqSpec)
                .basePath("d22b8953-c87e-4729-afb8-fbce9d1dc9e4")
                .delete();
    }
}
