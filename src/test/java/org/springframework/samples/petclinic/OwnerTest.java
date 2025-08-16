package org.springframework.samples.petclinic;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
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
@Feature("Owner Model Unit Tests")
public class OwnerTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(description = "Should find owner by ID")
    @Story("Find Owner")
    @Severity(SeverityLevel.NORMAL)
    public void testFindOwnerById() {
        Owner owner = new Owner();
        owner.setId(1);
        when(ownerRepository.findById(1)).thenReturn(owner);

        Owner result = clinicService.findOwnerById(1);
        assertNotNull(result);
        assertEquals(result.getId(), Integer.valueOf(1));
    }

    @Test(description = "Should save owner")
    @Story("Valid owner creation")
    @Severity(SeverityLevel.CRITICAL)
    public void testSaveOwner() {
        Owner owner = new Owner();
        owner.setId(11);
        owner.setFirstName("Lucas");
        owner.setLastName("Lorenzo");
        
        doNothing().when(ownerRepository).save(owner);
        clinicService.saveOwner(owner);

        verify(ownerRepository).save(owner);
    }

    @Test(description = "Should delete owner")
    @Story("Delete valid owner")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteOwner() {
        Owner owner = new Owner();
        owner.setId(2);

        doNothing().when(ownerRepository).delete(owner);
        clinicService.deleteOwner(owner);

        verify(ownerRepository).delete(owner);
    }

    @Test(description = "Should set and get first name")
    @Story("Owner Getter/Setter")
    public void testSetAndGetFirstName() {
        Owner owner = new Owner();
        owner.setFirstName("Jane");
        assertEquals(owner.getFirstName(), "Jane");
    }
} 