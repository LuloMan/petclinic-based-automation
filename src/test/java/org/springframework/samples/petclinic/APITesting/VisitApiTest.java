package org.springframework.samples.petclinic.APITesting;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class VisitApiTest extends BaseApiTest {

    private static int visitId;

    @Test(dataProvider = "visitsData", dataProviderClass = CsvDataProvider.class, priority = 1)
    public void createVisit(String date, String description, String ownerId, String petId) {
        visitId =
        given()
            .contentType(ContentType.JSON)
            .log().all()
            .body("{\"date\":\""+date+"\",\"description\":\""+description+"\"}")
        .when()
            .post("/owners/"+ownerId+"/pets/"+petId+"/visits")
        .then()
            .statusCode(201)
            .body("description", equalTo(description))
            .extract().path("id");
    }

    @Test(priority = 2)
    public void getVisitById() {
        given()
            .pathParam("id", visitId)
        .when()
            .get("/visits/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(visitId));
    }

    @Test(priority = 3)
    public void updateVisit() {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", visitId)
            .body("{\"id\":"+visitId+",\"date\":\"2025-08-25\",\"description\":\"Updated Description\"}")
        .when()
            .put("/visits/{id}")
        .then()
            .statusCode(204);
    }

    @Test(priority = 4)
    public void deleteVisits() {
        given()
            .pathParam("id", visitId)
        .when()
            .delete("/visits/{id}")
        .then()
            .statusCode(204);
    }
}