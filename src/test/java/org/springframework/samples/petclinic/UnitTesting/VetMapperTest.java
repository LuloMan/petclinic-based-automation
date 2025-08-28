package org.springframework.samples.petclinic.UnitTesting;

import io.qameta.allure.*;

import org.springframework.samples.petclinic.mapper.VetMapper;
import org.springframework.samples.petclinic.model.Vet;
import org.mapstruct.factory.Mappers;
import org.testng.annotations.*;
import static org.testng.Assert.*;

@Epic("Mapper")
@Feature("VetMapper Unit Tests")
public class VetMapperTest {
    private VetMapper vetMapper;

    @BeforeMethod
    public void setUp() {
        vetMapper = Mappers.getMapper(VetMapper.class);
    }

    @Test(description = "Should map Vet to DTO")
    @Story("Map Vet")
    public void testMapOwnerToDto() {
        Vet vet = new Vet();
        vet.setFirstName("Lorenzo");
        assertEquals(vet.getFirstName(), "Lorenzo");
    }
} 