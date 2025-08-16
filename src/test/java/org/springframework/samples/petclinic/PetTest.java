package org.springframework.samples.petclinic;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
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
@Feature("Pet Model Unit Tests")
public class PetTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(description = "Should set and get name")
    @Story("Pet Getter/Setter")
    public void testSetAndGetName() {
        Pet pet = new Pet();
        pet.setName("Luna");
        assertEquals(pet.getName(), "Luna");
    }

    @Test(description = "Should save valid pet")
    @Story("Valid pet creation")
    @Severity(SeverityLevel.CRITICAL)
    public void testSavePet() {
        PetType type = new PetType();
        type.setId(11);
        type.setName("Rana");
        Pet pet = new Pet();
        pet.setName("Luna");
        pet.setType(type);

        doNothing().when(petTypeRepository).save(type);
        clinicService.savePetType(type);

        doNothing().when(petRepository).save(pet);
        clinicService.savePet(pet);

        verify(petRepository).save(pet);
    }

    @Test(description = "Should delete Pet")
    @Story("Delete valid pet")
    @Severity(SeverityLevel.NORMAL)
    public void testDeletePet() {
        Pet pet = new Pet();
        pet.setId(2);

        doNothing().when(petRepository).delete(pet);
        clinicService.deletePet(pet);

        verify(petRepository).delete(pet);
    }

    @Test(description = "Should find pet by ID")
    @Story("Find Pet")
    @Severity(SeverityLevel.NORMAL)
    public void testFindPetById() {
        Pet pet = new Pet();
        pet.setId(1);
        when(petRepository.findById(1)).thenReturn(pet);

        Pet result = clinicService.findPetById(1);
        assertNotNull(result);
        assertEquals(result.getId(), Integer.valueOf(1));
    }
} 