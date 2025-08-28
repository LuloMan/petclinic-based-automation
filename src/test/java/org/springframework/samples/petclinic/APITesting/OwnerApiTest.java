package org.springframework.samples.petclinic.APITesting;

import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OwnerApiTest extends BaseApiTest{

    private static int ownerId;

    @Test(dataProvider = "ownersData", dataProviderClass = CsvDataProvider.class,priority = 1)
    public void createOwner(String firstName, String lastName, String address, String city, String telephone) {
        ownerId =
        given()
            .contentType(ContentType.JSON)
            .body("{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"address\":\""+address+"\",\"city\":\""+city+"\",\"telephone\":\""+telephone+"\"}")
        .when()
            .post("/owners")
        .then()
            .statusCode(201)
            .body("firstName", equalTo(firstName))
            .extract().path("id");
    }

    @Test(priority = 2)
    public void getOwnerById() {
        given()
            .pathParam("id", ownerId)
        .when()
            .get("/owners/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(ownerId));
    }

    @Test(priority = 3)
    public void updateOwner() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":" + ownerId + ",\"firstName\":\"Updated\",\"lastName\":\"User\",\"address\":\"Updated St\",\"city\":\"Updated City\",\"telephone\":\"6543217890\"}")
        .when()
            .put("/owners/" + ownerId)
        .then()
            .statusCode(204);
    }

    @Test(priority = 4)
    public void deleteOwner() {
        given()
            .pathParam("id", ownerId)
        .when()
            .delete("/owners/{id}")
        .then()
            .statusCode(204);
    }
}
