package org.springframework.samples.petclinic.APITesting;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetTypeApiTest extends BaseApiTest {

    private static List<Integer> petTypeIds = new ArrayList<>();
    private static final List<String> petTypeNames = Arrays.asList("fish", "rat", "spider");

    @Test(priority = 1)
    public void createPetTypes() {
        for (String name : petTypeNames) {
            int id =
            given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"" + name + "\"}")
            .when()
                .post("/pettypes")
            .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .extract().path("id");

            petTypeIds.add(id);
        }
    }

    @Test(priority = 2)
    public void getPetTypesById() {
        for (Integer id : petTypeIds) {
            given()
                .pathParam("id", id)
            .when()
                .get("/pettypes/{id}")
            .then()
                .statusCode(200)
                .body("id", equalTo(id));
        }
    }

    @Test(priority = 3)
    public void updateFirstPetType() {
        Integer id = petTypeIds.get(0);

        given()
            .contentType(ContentType.JSON)
            .pathParam("id", id)
            .body("{\"id\":"+id+",\"name\":\"updated-pettype\"}")
        .when()
            .put("/pettypes/{id}")
        .then()
            .statusCode(204);
    }

    @Test(priority = 4)
    public void deletePetTypes() {
        for (Integer id : petTypeIds) {
            given()
                .pathParam("id", id)
            .when()
                .delete("/pettypes/{id}")
            .then()
                .statusCode(204);
        }
    }
}