package org.springframework.samples.petclinic.APITesting;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }
}
