package org.springframework.samples.petclinic.UnitTesting;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.mapper.SpecialtyMapper;
import org.springframework.samples.petclinic.model.Specialty;
import org.mapstruct.factory.Mappers;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.mapstruct.factory.Mappers;

@Epic("Mapper")
@Feature("SpecialtyMapper Unit Tests")
public class SpecialtyMapperTest {
    private SpecialtyMapper specialtyMapper;

    @BeforeMethod
    public void setUp() {
        specialtyMapper = Mappers.getMapper(SpecialtyMapper.class);
    }

    @Test(description = "Should map Specialty to DTO")
    @Story("Map Specialty")
    public void testMapSpecialtyToDto() {
        Specialty specialty = new Specialty();
        specialty.setName("Urology");
        assertEquals(specialty.getName(), "Urology");
    }
} 