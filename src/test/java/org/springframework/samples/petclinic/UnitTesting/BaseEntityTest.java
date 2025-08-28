package org.springframework.samples.petclinic.UnitTesting;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.testng.annotations.*;
import static org.testng.Assert.*;

@Epic("Model")
@Feature("BaseEntity Model Unit Tests")
public class BaseEntityTest {
    @Test(description = "Should set and get ID")
    @Story("BaseEntity Getter/Setter")
    public void testSetAndGetId() {
        BaseEntity entity = new BaseEntity();
        entity.setId(42);
        assertEquals(entity.getId(), Integer.valueOf(42));
    }
} 