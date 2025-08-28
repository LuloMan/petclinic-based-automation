package org.springframework.samples.petclinic.APITesting;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetsApiTest extends BaseApiTest {

    private static Integer petId;

    @Test(dataProvider = "petsData", dataProviderClass = CsvDataProvider.class, priority = 1)
    public void createPet(String name, String birthDate, String typeName, String typeId) {
        petId =
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\""+name+"\",\"birthDate\":\""+birthDate+"\",\"type\":{\"name\":\""+typeName+"\",\"id\":"+typeId+"}}")
        .when()
            .post("/owners/" + 5 + "/pets")
        .then()
            .statusCode(201)
            .body("name", equalTo(name))
            .extract().path("id");
    }

    @Test(priority = 2)
    public void getPetById() {
        given()
            .pathParam("id", petId)
        .when()
            .get("/pets/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(petId));
    }

    @Test(priority = 3)
    public void updatePet() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"Updated\",\"birthDate\":\"2021-07-15\",\"type\":{\"name\":\"bird\",\"id\":5}}")
        .when()
            .put("/pets/" + petId)
        .then()
            .statusCode(204);
    }

    @Test(priority = 4)
    public void deletePet() {
        given()
            .pathParam("id", petId)
        .when()
            .delete("/pets/{id}")
        .then()
            .statusCode(204);
    }
}
