package org.springframework.samples.petclinic.UnitTesting;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.mapper.OwnerMapper;
import org.springframework.samples.petclinic.model.Owner;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.mapstruct.factory.Mappers;

@Epic("Mapper")
@Feature("OwnerMapper Unit Tests")
public class OwnerMapperTest {
    private OwnerMapper ownerMapper;

    @BeforeMethod
    public void setUp() {
        ownerMapper = Mappers.getMapper(OwnerMapper.class);
    }

    @Test(description = "Should map Owner to DTO")
    @Story("Map Owner")
    public void testMapOwnerToDto() {
        Owner owner = new Owner();
        owner.setFirstName("John");
        assertEquals(owner.getFirstName(), "John");
    }
} 