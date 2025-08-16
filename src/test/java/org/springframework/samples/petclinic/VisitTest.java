package org.springframework.samples.petclinic;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VisitRepository;
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
@Feature("Visit Model Unit Tests")
public class VisitTest {
    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(description = "Should set and get id")
    @Story("Visit Getter/Setter")
    public void testSetAndGetId() {
        Visit visit = new Visit();
        visit.setId(10);
        assertEquals(visit.getId(), 10);
    }

    @Test(description = "Should find visit by ID")
    @Story("Find Visit")
    @Severity(SeverityLevel.NORMAL)
    public void testFindVisitById() {
        Visit visit = new Visit();
        visit.setId(1);
        when(visitRepository.findById(1)).thenReturn(visit);

        Visit result = clinicService.findVisitById(1);
        assertNotNull(result);
        assertEquals(result.getId(), Integer.valueOf(1));
    }

    @Test(description = "Should save visit")
    @Story("Valid visit creation")
    @Severity(SeverityLevel.CRITICAL)
    public void testSaveVisit() {
        Visit visit = new Visit();
        visit.setId(11);
        visit.setDescription("Broken leg");
        
        doNothing().when(visitRepository).save(visit);
        clinicService.saveVisit(visit);

        verify(visitRepository).save(visit);
    }

    @Test(description = "Should delete visit")
    @Story("Delete valid visit")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteVisit() {
        Visit visit = new Visit();
        visit.setId(2);

        doNothing().when(visitRepository).delete(visit);
        clinicService.deleteVisit(visit);

        verify(visitRepository).delete(visit);
    }
} 