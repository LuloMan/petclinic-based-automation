package org.springframework.samples.petclinic.APITesting;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("PetClinic API Tests")
@Feature("Specialties")
public class SpecialtiesApiTest extends BaseApiTest {

    private static List<Integer> specialtyIds = new ArrayList<>();
    private static final List<String> specialtyNames = Arrays.asList("anesthetist", "cardiology", "neurology");

    @Story("Create Specialty")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void createSpecialties() {
        for (String name : specialtyNames) {
            int id =
            given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"" + name + "\"}")
            .when()
                .post("/specialties")
            .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .extract().path("id");

            specialtyIds.add(id);
        }
    }

    @Story("Get Specialty")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void getSpecialtiesById() {
        for (Integer id : specialtyIds) {
            given()
                .pathParam("id", id)
            .when()
                .get("/specialties/{id}")
            .then()
                .statusCode(200)
                .body("id", equalTo(id));
        }
    }

    @Story("Update Specialty")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void updateFirstSpecialty() {
        Integer id = specialtyIds.get(0);

        given()
            .contentType(ContentType.JSON)
            .pathParam("id", id)
            .body("{\"id\":"+id+",\"name\":\"updated-specialty\"}")
        .when()
            .put("/specialties/{id}")
        .then()
            .statusCode(204);
    }

    @Story("Delete Specialty")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void deleteSpecialties() {
        for (Integer id : specialtyIds) {
            given()
                .pathParam("id", id)
            .when()
                .delete("/specialties/{id}")
            .then()
                .statusCode(204);
        }
    }
}