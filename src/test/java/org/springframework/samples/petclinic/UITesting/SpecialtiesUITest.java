package org.springframework.samples.petclinic.UITesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.*;

@Epic("PetClinic UI Tests")
@Feature("Specialties")
public class SpecialtiesUITest extends BaseUITest {

    @Story("Specialty Creation By CSV")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createSpecialiesByCSV() {
        var specialtyPage = homePage.clickSpecialtiesPage();
        int contador = specialtyPage.specialtyList().size();
        specialtyPage.uploadCSV();
        specialtyPage.CSVbutton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> specialtyPage.specialtyList().size() > contador);
        int contadorFinal = specialtyPage.specialtyList().size();
        Assert.assertTrue(contadorFinal>contador);
    }

    @Story("Specialty Invalid Creation By CSV")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createSpecialiesInvalidByCSV() {
        var specialtyPage = homePage.clickSpecialtiesPage();
        int contador = specialtyPage.specialtyList().size();
        specialtyPage.CSVbutton();

        int contadorFinal = specialtyPage.specialtyList().size();
        Assert.assertTrue(contadorFinal==contador);
    }

    @Story("Specialty Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createSpecialty() {
        var specialtyPage = homePage.clickSpecialtiesPage();
        int contador = specialtyPage.specialtyList().size();
        specialtyPage.setSpecialty("Odontologist");
        specialtyPage.clickSaveButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> specialtyPage.specialtyList().size() > contador);
        int contadorFinal = specialtyPage.specialtyList().size();
        Assert.assertTrue(contadorFinal>contador);
    }

    @Story("Specialty Invalid Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createSpecialtyInvalid() {
        var specialtyPage = homePage.clickSpecialtiesPage();
        int contador = specialtyPage.specialtyList().size();
        specialtyPage.setSpecialty("");
        specialtyPage.clickSaveButton();

        int contadorFinal = specialtyPage.specialtyList().size();
        Assert.assertTrue(contadorFinal==contador);
    }

    @Story("Specialty Update")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void updateSpecialty() throws InterruptedException {
        var specialtyPage = homePage.clickSpecialtiesPage();
        specialtyPage.clickEditButton("dentistry");
        driver.findElement(By.xpath("//*[@id=\"specialty-form\"]/input[2]")).clear();
        specialtyPage.setSpecialty("Updated");
        specialtyPage.clickSaveButton();

        Thread.sleep(2000);
        String newName = driver.findElement(
        By.xpath("//tr[td[normalize-space(.)='Updated']]/td[2]")).getDomProperty("textContent").trim();
        Assert.assertEquals(newName, "Updated");
    }

    @Story("Specialty Delete")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void deleteSpecialty() throws InterruptedException {
       var specialtyPage = homePage.clickSpecialtiesPage();
       int contador = specialtyPage.specialtyList().size();
       specialtyPage.clickDeleteButton("surgery");

       Thread.sleep(2000);
       int contadorFinal = specialtyPage.specialtyList().size();
       Assert.assertTrue(contadorFinal<contador);
    }

}
