package org.springframework.samples.petclinic.UnitTesting;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.model.Vet;
import org.testng.annotations.*;

import static org.testng.Assert.*;


@Epic("Model")
@Feature("Vet Model Unit Tests")
public class VetTest {

    @Test(description = "Should set and get first name")
    @Story("Vet Getter/Setter")
    public void testSetAndGetFirstName() {
        Vet vet = new Vet();
        vet.setFirstName("Juana");
        assertEquals(vet.getFirstName(), "Juana");
    }

} 