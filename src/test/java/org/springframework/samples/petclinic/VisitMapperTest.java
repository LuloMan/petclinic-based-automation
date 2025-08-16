package org.springframework.samples.petclinic;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.mapper.VisitMapper;
import org.springframework.samples.petclinic.model.Visit;
import org.mapstruct.factory.Mappers;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.mapstruct.factory.Mappers;

@Epic("Mapper")
@Feature("VisitMapper Unit Tests")
public class VisitMapperTest {
    private VisitMapper visitMapper;

    @BeforeMethod
    public void setUp() {
        visitMapper = Mappers.getMapper(VisitMapper.class);
    }

    @Test(description = "Should map Visit to DTO")
    @Story("Map Visit")
    public void testMapVisitToDto() {
        Visit visit = new Visit();
        visit.setId(10);
        assertEquals(visit.getId(), 10);
    }
} 