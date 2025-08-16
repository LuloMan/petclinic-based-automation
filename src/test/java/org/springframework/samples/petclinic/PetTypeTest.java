package org.springframework.samples.petclinic;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetTypeRepository;
import org.springframework.samples.petclinic.service.ClinicServiceImpl;
import org.testng.annotations.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Epic("Model")
@Feature("PetType Model Unit Tests")
public class PetTypeTest {

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(description = "Should set and get name")
    @Story("PetType Getter/Setter")
    public void testSetAndGetName() {
        PetType petType = new PetType();
        petType.setName("Frog");
        assertEquals(petType.getName(), "Frog");
    }

    @Test(description = "Should find pet type by ID")
    @Story("Find PetType")
    @Severity(SeverityLevel.NORMAL)
    public void testFindPetTypeById() {
        PetType type = new PetType();
        type.setId(1);
        when(petTypeRepository.findById(1)).thenReturn(type);

        PetType result = clinicService.findPetTypeById(1);
        assertNotNull(result);
        assertEquals(result.getId(), Integer.valueOf(1));
    }

    @Test(description = "Should save pet type")
    @Story("Valid PetType creation")
    @Severity(SeverityLevel.CRITICAL)
    public void testSavePetType() {
        PetType type = new PetType();
        type.setId(11);
        type.setName("Frog");
        
        doNothing().when(petTypeRepository).save(type);
        clinicService.savePetType(type);

        verify(petTypeRepository).save(type);
    }

    @Test(description = "Should delete pet type")
    @Story("Delete valid PetType")
    @Severity(SeverityLevel.NORMAL)
    public void testDeletePetType() {
        PetType type = new PetType();
        type.setId(2);

        doNothing().when(petTypeRepository).delete(type);
        clinicService.deletePetType(type);

        verify(petTypeRepository).delete(type);
    }
} 