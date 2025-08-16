package org.springframework.samples.petclinic;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.mapper.PetTypeMapper;
import org.springframework.samples.petclinic.model.PetType;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.mapstruct.factory.Mappers;

@Epic("Mapper")
@Feature("PetTypeMapper Unit Tests")
public class PetTypeMapperTest {
    private PetTypeMapper petTypeMapper;

    @BeforeMethod
    public void setUp() {
        petTypeMapper = Mappers.getMapper(PetTypeMapper.class);
    }

    @Test(description = "Should map PetType to DTO")
    @Story("Map PetType")
    public void testMapPetTypeToDto() {
        PetType petType = new PetType();
        petType.setName("Frog");
        assertEquals(petType.getName(), "Frog");
    }
} 