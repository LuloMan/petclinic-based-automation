package org.springframework.samples.petclinic.UITesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.*;

@Epic("PetClinic UI Tests")
@Feature("PetTypes")
public class PetTypesUITest extends BaseUITest {

    @Story("PetType Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createPetType() {
    var petTypePage = homePage.clickPetTypesPage();
    int contador = petTypePage.petTypesList().size();
    petTypePage.setPetTypes("rat");
    petTypePage.clickSaveButton();

    By loadingOverlay = By.id("loading-overlay");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingOverlay));

    int contadorFinal = petTypePage.petTypesList().size();
    Assert.assertTrue(contadorFinal > contador);
    }

    @Story("PetType Invalid Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createPetTypeInvalid() {
    var petTypePage = homePage.clickPetTypesPage();
    int contador = petTypePage.petTypesList().size();
    petTypePage.setPetTypes("");
    petTypePage.clickSaveButton();

    By loadingOverlay = By.id("loading-overlay");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingOverlay));

    int contadorFinal = petTypePage.petTypesList().size();
    Assert.assertTrue(contadorFinal==contador);
    }

    @Story("PetType Update")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void updatePetType() {
        var petTypePage = homePage.clickPetTypesPage();
        petTypePage.clickEditButton("hamster");
        driver.findElement(By.xpath("/html/body/main/section[6]/form/input[2]")).clear();
        petTypePage.setPetTypes("Updated");
        petTypePage.clickSaveButton();

        By loadingOverlay = By.id("loading-overlay");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingOverlay));
        String newName = driver.findElement(
        By.xpath("//tr[td[normalize-space(.)='Updated']]/td[2]")).getDomProperty("textContent").trim();
        Assert.assertEquals(newName, "Updated");
    }

    @Story("PetType Delete")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void deletePetType() throws InterruptedException {
       var petTypePage = homePage.clickPetTypesPage();
       int contador = petTypePage.petTypesList().size();
       petTypePage.clickDeleteButton("24");

       Thread.sleep(2000);
       int contadorFinal = petTypePage.petTypesList().size();
       Assert.assertTrue(contadorFinal<contador);
    }
}
