package org.springframework.samples.petclinic.UITesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.*;

@Epic("PetClinic UI Tests")
@Feature("Pets")
public class PetsUITest extends BaseUITest {

    @Story("Pet Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void createPet() {
        var petPage = homePage.clickPetsPage();
        int contador = petPage.petList().size();
        petPage.setPet("Michi", 4, 12, 2016, "4", "bird");
        petPage.clickSaveButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pets\"]")));
        int contadorFinal = petPage.petList().size();
        Assert.assertTrue(contadorFinal>contador);
    }

    @Story("Pet Update")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updatePet() throws InterruptedException {
        var petPage = homePage.clickPetsPage();
        petPage.clickEditButton("Iggy");
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("ownerId")).clear();
        petPage.setPet("Updated", 4, 12, 2016,"5", "bird");
        petPage.clickSaveButton();

        Thread.sleep(2000);
        String newName = driver.findElement(
        By.xpath("//tr[td[normalize-space(.)='Updated']]/td[2]")).getDomProperty("textContent").trim();
        Assert.assertEquals(newName, "Updated");
    }

    @Story("Pet Delete")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void deletePet() throws InterruptedException {
        var petPage = homePage.clickPetsPage();
        int contador = petPage.petList().size();
        petPage.clickDeleteButton("Luna");

        Thread.sleep(2000);
        int contadorFinal = petPage.petList().size();
        Assert.assertTrue(contadorFinal<contador);
    }
}
