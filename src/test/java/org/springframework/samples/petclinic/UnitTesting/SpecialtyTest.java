package org.springframework.samples.petclinic.UnitTesting;

import io.qameta.allure.*;

import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
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
@Feature("Specialty Model Unit Tests")
public class SpecialtyTest {
    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(description = "Should set and get name")
    @Story("Specialty Getter/Setter")
    public void testSetAndGetName() {
        Specialty specialty = new Specialty();
        specialty.setName("Urology");
        assertEquals(specialty.getName(), "Urology");
    }

    @Test(description = "Should save valid specialty")
    @Story("Valid specialty creation")
    @Severity(SeverityLevel.CRITICAL)
    public void testSaveSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setId(11);
        specialty.setName("Dermatology");

        doNothing().when(specialtyRepository).save(specialty);
        clinicService.saveSpecialty(specialty);

        verify(specialtyRepository).save(specialty);
    }

    @Test(description = "Should delete Specialty")
    @Story("Delete valid Specialty")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setId(2);

        doNothing().when(specialtyRepository).delete(specialty);
        clinicService.deleteSpecialty(specialty);

        verify(specialtyRepository).delete(specialty);
    }

    @Test(description = "Should find specialty by ID")
    @Story("Find Specialty")
    @Severity(SeverityLevel.NORMAL)
    public void testFindSpecialtyById() {
        Specialty specialty = new Specialty();
        specialty.setId(1);
        when(specialtyRepository.findById(1)).thenReturn(specialty);

        Specialty result = clinicService.findSpecialtyById(1);
        assertNotNull(result);
        assertEquals(result.getId(), Integer.valueOf(1));
    }

} 