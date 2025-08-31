package org.springframework.samples.petclinic.APITesting;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("PetClinic API Tests")
@Feature("Pets")
public class PetsApiTest extends BaseApiTest {

    private static Integer petId;

    @Story("Create Pet")
    @Severity(SeverityLevel.CRITICAL)
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

    @Story("Get Pet")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void getPetById() {
        given()
            .pathParam("id", 5)
        .when()
            .get("/pets/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(5));
    }

    @Story("Update Pet")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void updatePet() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"Updated\",\"birthDate\":\"2021-07-15\",\"type\":{\"name\":\"bird\",\"id\":5}}")
        .when()
            .put("/pets/" + 5)
        .then()
            .statusCode(204);
    }

    @Story("Delete Pet")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void deletePet() {
        given()
            .pathParam("id", 6)
        .when()
            .delete("/pets/{id}")
        .then()
            .statusCode(204);
    }
}
